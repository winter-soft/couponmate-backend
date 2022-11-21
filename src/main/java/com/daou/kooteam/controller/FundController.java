package com.daou.kooteam.controller;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.service.FundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/fund")
@RequiredArgsConstructor
public class FundController {
    private final FundService fundService;

    @GetMapping("/funding/{fundId}")
    @ApiOperation(value = "펀딩 상세 조회", notes = "fundId를 받아 출력한다.")
    public ResponseDTO<?> selectFund(@PathVariable int fundId){
        return fundService.selectFund(fundId);

    }

    @GetMapping("/{itemId}")
    @ApiOperation(value = "본인의 펀딩 생성", notes = "itemId를 받아 생성한다.")
    public ResponseDTO<?> createFunding(@AuthenticationPrincipal String userId,
                                        @PathVariable String itemId){
        return fundService.createFunding(userId, Integer.parseInt(itemId));
    }

    @GetMapping("/{itemId}/{user2}")
    @ApiOperation(value = "팔로워의 펀딩 생성", notes = "itemId를 받아 생성한다.")
    public ResponseDTO<?> createFollowerFunding(@PathVariable String itemId,
                                                @PathVariable String user2){
        return fundService.createFunding(user2, Integer.parseInt(itemId));
    }
}
