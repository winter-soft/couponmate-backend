package com.daou.kooteam.repository;

import com.daou.kooteam.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
