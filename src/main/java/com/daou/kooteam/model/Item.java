package com.daou.kooteam.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends TimeZone {
    @Id
    @GeneratedValue
    private int item_id;

    @ManyToOne // EAGER
    @JoinColumn(name = "company_id")
    private Company company; // Company (외래키)

    @Column(nullable = false)
    private String item_brand;

    private String item_image_url;

    @Column(nullable = false, length = 50)
    private String item_name;

    @Column(nullable = false)
    private int item_price;

    private String hashtag;

    @Lob
    private String item_infor;
}
