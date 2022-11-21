package com.daou.kooteam.dto.user;

import com.daou.kooteam.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String id;
    private String platformType;
    private String platformId;
    private String email;
    private String profileImageUrl;
    private String username;
    private String phoneNumber;

    public static UserDTO entityToDTO(User user){
        return UserDTO.builder()
                .id(user.getUser_id())
                .platformType(user.getPlatform_type())
                .platformId(user.getPlatform_id())
                .email(user.getEmail())
                .profileImageUrl(user.getProfile_image_url())
                .username(user.getUsername())
                .phoneNumber(user.getPhone_number())
                .build();
    }
}
