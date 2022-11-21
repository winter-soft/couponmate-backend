package com.daou.kooteam.controller;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.dto.user.UserPhoneNumberDTO;
import com.daou.kooteam.service.FollowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/")
    @ApiOperation(value = "phone_number를 통해 팔로잉하기", notes = "phone_number 넘겨주세요")
    public ResponseDTO<?> followUserWithPhoneNumber(@AuthenticationPrincipal String user,
                                                    @RequestBody UserPhoneNumberDTO phoneNumber){
        return followService.followUserWithPhoneNumber(user, phoneNumber);
    }

    @GetMapping("/{user2}")
    @ApiOperation(value = "user2를 팔로잉한다.", notes = "현재 유저가 USER2를 팔로잉한다. 이미 되어있을경우 팔로우 취소")
    public ResponseDTO<?> followUser(@AuthenticationPrincipal String userId,
                                     @PathVariable String user2){
        return followService.followUser(userId, user2);
    }


    @GetMapping("/following")
    @ApiOperation(value = "해당 유저의 팔로잉한 사람을 내려줌", notes = "해당 유저의 팔로잉한 사람을 내려줌")
    public ResponseDTO<?> followUser(@AuthenticationPrincipal String userId){
        return followService.followUser(userId);
    }
}
