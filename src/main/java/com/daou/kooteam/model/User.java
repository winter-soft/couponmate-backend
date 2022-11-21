package com.daou.kooteam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String user_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String birthday;

    @JsonIgnore
    @Column(nullable = false)
    private String platform_type;

    @JsonIgnore
    @Column(nullable = false)
    private String platform_id;

    @Column(nullable = false)
    private String email;

    private String profile_image_url;

    @JsonIgnore
    private String phone_number;

    @JsonIgnore
    private String token;

    public void setToken(String token) {
        this.token = token;
    }
}
