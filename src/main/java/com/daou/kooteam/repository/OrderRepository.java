package com.daou.kooteam.repository;

import com.daou.kooteam.model.Order;
import com.daou.kooteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    public List<Order> findAllByUser(User user);
}
