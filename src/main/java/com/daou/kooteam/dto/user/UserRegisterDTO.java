package com.daou.kooteam.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private String platformType;
    private String platformId;
    private String email;
    private String username;
    private String profileImageUrl;
    private String phoneNumber;
    private String birthday;
}
