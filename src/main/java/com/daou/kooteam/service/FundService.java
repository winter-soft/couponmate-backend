package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.exception.fund.AlreadyExistFundException;
import com.daou.kooteam.exception.fund.FundNotFoundException;
import com.daou.kooteam.exception.item.ItemNotFoundException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.Fund;
import com.daou.kooteam.model.Item;
import com.daou.kooteam.model.User;
import com.daou.kooteam.repository.DonationRepository;
import com.daou.kooteam.repository.FundRepository;
import com.daou.kooteam.repository.ItemRepository;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FundService {
    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final ItemRepository itemRepository;
    private final DonationRepository donationRepository;

    public ResponseDTO<?> selectFund(int fundId) {
        Fund fund = fundRepository.findById(fundId).orElseThrow(() -> {
            throw new FundNotFoundException();
        });

        fund.setDonates(donationRepository.findByFund(fund));

        return new ResponseDTO<>(HttpStatus.OK.value(), fund);
    }

    public ResponseDTO<?> createFunding(String userId, int itemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new ItemNotFoundException();
        });

        Fund fund = fundRepository.findByUserAndItem(user, item);

        if(fund == null){
            fund = fundRepository.save(Fund.builder()
                    .user(user)
                    .item(item)
                    .build());
        }else{
            throw new AlreadyExistFundException();
        }

        fund.calPrice(); // 계산

        return new ResponseDTO<>(HttpStatus.OK.value(), fund);
    }
}
