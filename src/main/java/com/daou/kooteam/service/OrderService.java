package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.Order;
import com.daou.kooteam.model.OrderStatus;
import com.daou.kooteam.model.User;
import com.daou.kooteam.repository.OrderRepository;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ResponseDTO<?> selectOrder(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        List<Order> orders = orderRepository.findAllByUser(user);
        orders.removeIf(o -> (o.getOrderStatus().equals(OrderStatus.WAITING)));

        return new ResponseDTO<>(HttpStatus.OK.value(), orders);
    }
}
