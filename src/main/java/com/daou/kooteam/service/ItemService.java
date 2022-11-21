package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.dto.item.ItemDetailDTO;
import com.daou.kooteam.exception.item.ItemNotFoundException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.Item;
import com.daou.kooteam.model.User;
import com.daou.kooteam.model.Wish;
import com.daou.kooteam.repository.ItemRepository;
import com.daou.kooteam.repository.UserRepository;
import com.daou.kooteam.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ResponseDTO<?> selectItemDetail(String userId,
                                           int itemId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new ItemNotFoundException();
        });

        Wish byUserAndItem = wishRepository.findByUserAndItem(user, item);
        boolean isCheck = false;
        if(byUserAndItem != null) isCheck = true;

        return new ResponseDTO<>(HttpStatus.OK.value(), ItemDetailDTO.builder()
                .item(item)
                .wishCheck(isCheck)
                .build());
    }
}
