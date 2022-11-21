package com.daou.kooteam.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDTO {
    private int fund_id;
    private int item_id;
    private String order_name;
    private int amount;
}
