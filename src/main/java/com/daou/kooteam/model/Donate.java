package com.daou.kooteam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donate extends TimeZone{
    @Id
    @GeneratedValue
    private int donate_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user; // User 외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id")
    @JsonIgnore
    private Fund fund; // Fund 외래키

    private int donation_price = 0; // 도네이션할 가격
    private String donation_message; // 도네이션 메시지
}
