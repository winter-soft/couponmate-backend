package com.daou.kooteam.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id @GeneratedValue
    private int company_id;

    private String company_name;
    @Lob
    private String company_infor;
}
