package com.daou.kooteam.controller;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.exception.item.ItemNotFoundException;
import com.daou.kooteam.model.Item;
import com.daou.kooteam.repository.ItemRepository;
import com.daou.kooteam.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/")
    @ApiOperation(value = "아이템 가져오기", notes = "아이템을 가져온다.")
    public ResponseDTO<?> selectItem(){
        return new ResponseDTO<>(HttpStatus.OK.value(), itemRepository.findAll());
    }

    @GetMapping("/detail/{itemId}")
    @ApiOperation(value = "아이템 상세", notes = "아이템 정보, 회사, wishCheck 정보를 리턴")
    public ResponseDTO<?> selectItemDetail(@AuthenticationPrincipal String userId,
                                           @PathVariable String itemId){
        return itemService.selectItemDetail(userId, Integer.parseInt(itemId));
    }
}
