package com.daou.kooteam.dto.donation;

import com.daou.kooteam.model.Donate;
import com.daou.kooteam.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationResDTO {
    private User user;
    private DonationReqDTO donate;

    public static DonationResDTO of(User user, DonationReqDTO reqDTO){
        return DonationResDTO.builder()
                .user(user)
                .donate(reqDTO)
                .build();

    }
}
