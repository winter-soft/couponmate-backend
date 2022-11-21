package com.daou.kooteam.controller;

import com.daou.kooteam.dto.payment.PaymentReqDTO;
import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.model.Order;
import com.daou.kooteam.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Value("${payments.toss.clientKey}")
    private String clientKey;

    @PostMapping("/valid/{fundId}")
    public ResponseDTO<?> paymentValid(@AuthenticationPrincipal String userId,
                                       @PathVariable int fundId,
                                       @RequestBody PaymentReqDTO reqDTO){
        return paymentService.paymentValid(userId, reqDTO, fundId);
    }

    @GetMapping("/success")
    public ResponseDTO<?> paymentConfirm(@RequestParam String paymentKey,
                                         @RequestParam String orderId,
                                         @RequestParam Integer amount){
        return paymentService.paymentConfirm(clientKey, paymentKey, orderId, amount);
    }
}
