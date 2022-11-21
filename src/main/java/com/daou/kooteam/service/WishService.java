package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WishService {
    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final ItemRepository itemRepository;

    public ResponseDTO<?> selectWish(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        List<Wish> wishes = wishRepository.findAllByUser(user);

        return new ResponseDTO<>(HttpStatus.OK.value(), wishes);
    }

    public ResponseDTO<?> addWish(String userId, int itemId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new ItemNotFoundException();
        });

        Wish wish = wishRepository.findByUserAndItem(user, item);

        if(wish == null){
            wish = wishRepository.save(Wish.builder()
                    .user(user)
                    .item(item)
                    .wishCheck(true)
                    .build());
        }else{
            wish.triggerWishCheck();
        }

        return new ResponseDTO<>(HttpStatus.OK.value(), wish);
    }

    public ResponseDTO<?> exploreWish(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        // following 하는 사람을 찾는다. user1 == userId
        // follower의 wish List를 가져온다. wishRepository.findByUser(user2)
        List<Wish> wishes = wishRepository.exploreWish(user);

        return new ResponseDTO<>(HttpStatus.OK.value(), wishes);
    }

}
