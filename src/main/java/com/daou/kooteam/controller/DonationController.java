package com.daou.kooteam.controller;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.dto.donation.DonationReqDTO;
import com.daou.kooteam.service.DonationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @PostMapping("/payment/{fundId}")
    @ApiOperation(value = "후원하기", notes = "후원하기")
    public ResponseDTO<?> donationPayment(@AuthenticationPrincipal String userId,
                                          @PathVariable int fundId,
                                          @RequestBody DonationReqDTO donationReqDTO){
        ResponseDTO<?> responseDTO = donationService.donationPayment(userId, fundId, donationReqDTO);
        donationService.calPrice(fundId);

        return responseDTO;
    }
}
