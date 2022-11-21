package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.dto.donation.DonationReqDTO;
import com.daou.kooteam.dto.donation.DonationResDTO;
import com.daou.kooteam.exception.fund.FundNotFoundException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.Donate;
import com.daou.kooteam.model.Fund;
import com.daou.kooteam.model.User;
import com.daou.kooteam.repository.DonationRepository;
import com.daou.kooteam.repository.FundRepository;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {
    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final DonationRepository donationRepository;

    public ResponseDTO<?> donationPayment(String userId, int fundId, DonationReqDTO donationReqDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Fund fund = fundRepository.findById(fundId).orElseThrow(() -> {
            throw new FundNotFoundException();
        });

        donationRepository.save(Donate.builder()
                .user(user)
                .fund(fund)
                .donation_message(donationReqDTO.getDonation_message())
                .donation_price(donationReqDTO.getDonation_price())
                .build());

        return new ResponseDTO<>(HttpStatus.OK.value(),
                DonationResDTO.of(user, donationReqDTO));
    }

    public void calPrice(int fundId){
        Fund fund = fundRepository.findById(fundId).orElseThrow(() -> {
            throw new FundNotFoundException();
        });

        List<Donate> byFund = donationRepository.findByFund(fund);

        fund.setDonates(byFund);
        fund.calPrice();
    }
}
