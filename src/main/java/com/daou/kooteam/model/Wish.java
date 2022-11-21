package com.daou.kooteam.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish extends TimeZone {

    @Id @GeneratedValue
    private int wish_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User (외래키)

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // Item (외래키)

    @ColumnDefault(value = "true")
    private boolean wishCheck = true;

    public void triggerWishCheck(){
        if(wishCheck) wishCheck = false;
        else wishCheck = true;
    }
}
