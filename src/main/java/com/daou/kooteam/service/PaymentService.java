package com.daou.kooteam.service;

import com.daou.kooteam.dto.payment.PaymentReqDTO;
import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.exception.fund.FundNotFoundException;
import com.daou.kooteam.exception.order.OrderNotFoundException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.*;
import com.daou.kooteam.repository.FundRepository;
import com.daou.kooteam.repository.OrderRepository;
import com.daou.kooteam.repository.PaymentRepository;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {
    private final FundRepository fundRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public ResponseDTO<?> paymentValid(final String userId,
                                       final PaymentReqDTO reqDTO,
                                       final int fundId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Fund fund = fundRepository.findById(fundId).orElseThrow(() -> {
            throw new FundNotFoundException();
        });

        Order order = Order.builder()
                .user(user)
                .item(fund.getItem())
                .order_name(reqDTO.getOrder_name())
                .amount(reqDTO.getAmount())
                .orderStatus(OrderStatus.WAITING)
                .build();
        Order save = orderRepository.save(order);

        Map<String, Object> m = new HashMap<>();
        m.put("amount", save.getAmount());
        m.put("orderId", save.getOrder_id());
        m.put("orderName", save.getOrder_name());
        m.put("customerName", user.getUsername());
        m.put("successUrl", "https://hongchanhui.shop/success");
        m.put("failUrl", "https://hongchanhui.shop/fail");

        return new ResponseDTO<>(HttpStatus.OK.value(), m);
    }

    @Transactional
    public ResponseDTO<?> paymentConfirm(final String clientKey,
                                         final String orderId,
                                         final String paymentKey,
                                         final Integer amount) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException();
        });

        try {
            URL address = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic " + clientKey);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);

            // tid 확인
            String parameter = "{\"paymentKey\":\"" + paymentKey +
                    "\",\"orderId\":\"" + orderId +
                    "\",\"amount\":\"" + amount + "\"}";
            log.info("parameter info {}", parameter);
            OutputStream outputStream = connection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(parameter);
            dataOutputStream.close();

            int result = connection.getResponseCode();

            InputStream inputStream;
            if(result == 200){
                inputStream = connection.getInputStream();
                log.info("result 200!");
            }else{
                inputStream = connection.getErrorStream();
                log.warn("result {}...", result);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                JSONObject obj = new JSONObject(bufferedReader.readLine());

                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), obj.toMap());
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            log.info("Toss Payment Approve! ");

            JSONObject obj = new JSONObject(bufferedReader.readLine());

            Map<String, Object> obj_map = obj.toMap();

            Payment payment = new Payment();
            if(obj_map.containsKey("version"))  payment.setVersion((String)obj_map.get("version"));
            if(obj_map.containsKey("paymentKey"))  payment.setPaymentKey((String) obj_map.get("paymentKey"));
            if(obj_map.containsKey("type"))  payment.setType((String) obj_map.get("type"));
            if(obj_map.containsKey("orderId"))  payment.setOrderId((String)obj_map.get("orderId"));
            if(obj_map.containsKey("orderName"))  payment.setOrderName((String)obj_map.get("orderName"));
            if(obj_map.containsKey("mId"))  payment.setMId((String)obj_map.get("mId"));
            if(obj_map.containsKey("currency"))  payment.setCurrency((String)obj_map.get("currency"));
            if(obj_map.containsKey("method"))  payment.setMethod((String)obj_map.get("method"));
            if(obj_map.containsKey("totalAmount"))  payment.setTotalAmount((Integer)obj_map.get("totalAmount"));
            if(obj_map.containsKey("balanceAmount"))  payment.setBalanceAmount((Integer)obj_map.get("balanceAmount"));
            if(obj_map.containsKey("status"))  payment.setStatus((String)obj_map.get("status"));
            if(obj_map.containsKey("transactionKey"))  payment.setTransactionKey((String)obj_map.get("transactionKey"));
            if(obj_map.containsKey("lastTransactionKey"))  payment.setLastTransactionKey((String)obj_map.get("lastTransactionKey"));
            if(obj_map.containsKey("suppliedAmount"))  payment.setSuppliedAmount((Integer)obj_map.get("suppliedAmount"));
            if(obj_map.containsKey("vat"))  payment.setVat((Integer)obj_map.get("vat"));

            Payment save = paymentRepository.save(payment);
            obj_map.put("payment_id", save.getPayment_id());

            order.setOrderStatus(OrderStatus.APPROVED);

            return new ResponseDTO<>(HttpStatus.OK.value(), payment);
        } catch (MalformedURLException e) {
            log.warn("Toss Payment Approve MalformedURLException Fail..");
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "error");
        } catch (IOException e) {
            log.warn("Toss Payment Approve IOException Fail..");
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "error");
        }
    }
}
