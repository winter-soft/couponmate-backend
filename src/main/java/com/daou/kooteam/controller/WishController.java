package com.daou.kooteam.controller;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.repository.WishRepository;
import com.daou.kooteam.service.WishService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @GetMapping("/")
    @ApiOperation(value = "본인의 Wish List 출력", notes = "본인의 Wish List 출력")
    public ResponseDTO<?> selectWish(@AuthenticationPrincipal String userId){
        return wishService.selectWish(userId);
    }

    @GetMapping("/{itemId}")
    @ApiOperation(value = "상품 즐겨찾기 추가", notes = "itemId를 넘겨주세요.")
    public ResponseDTO<?> addWish(@AuthenticationPrincipal String userId,
                                  @PathVariable int itemId) {
        return wishService.addWish(userId, itemId);
    }

    @GetMapping("/explore") // 쿼리 손보기
    @ApiOperation(value = "팔로잉한 사람의 Wish List 출력", notes = "")
    public ResponseDTO<?> exploreWish(@AuthenticationPrincipal String userId){
        return wishService.exploreWish(userId);
    }

}
