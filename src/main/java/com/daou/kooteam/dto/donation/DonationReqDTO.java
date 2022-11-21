package com.daou.kooteam.dto.donation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationReqDTO {
    private int donation_price = 0; // 도네이션할 가격
    private String donation_message; // 도네이션 메시지
}
