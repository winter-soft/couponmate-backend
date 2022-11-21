package com.daou.kooteam.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String order_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User (외래키)

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // Item (외래키)

    private String order_name;
    private int amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
