package com.daou.kooteam.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fund { // 받고싶은 선물 펀딩
    @Id @GeneratedValue
    private int fund_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User (외래키) 펀딩받는 사람

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // Item (외래키)

    @OneToMany(mappedBy = "fund")
    private List<Donate> donates = new ArrayList<>();

    private int funding_price; // 현재 펀딩된 가격
    private int remaining_price; // 남은 가격

    public void calPrice(){
        funding_price = 0;
        if(donates != null){
            for(Donate d : donates){
                funding_price += d.getDonation_price();
            }
        }

        int tmp = item.getItem_price() - funding_price;
        remaining_price = (tmp < 0) ? 0 : tmp;
    }

    public void setDonates(List<Donate> donates) {
        this.donates = donates;
    }
}
