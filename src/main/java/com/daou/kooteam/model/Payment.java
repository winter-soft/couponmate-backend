package com.daou.kooteam.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment extends TimeZone {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String payment_id;

    private int fund_id; // Fund (외래키) 펀딩금액 가져오기

    private String version;
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String mId;
    private String currency;
    private String method;
    private int totalAmount;
    private int balanceAmount;
    private String status;
    private String transactionKey;
    private String lastTransactionKey;
    private int suppliedAmount;
    private int vat;
}
