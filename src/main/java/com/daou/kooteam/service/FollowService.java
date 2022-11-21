package com.daou.kooteam.service;

import com.daou.kooteam.dto.ResponseDTO;
import com.daou.kooteam.dto.follow.FollowResDTO;
import com.daou.kooteam.dto.user.UserPhoneNumberDTO;
import com.daou.kooteam.exception.follow.FollowerNotFoundException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.model.Follow;
import com.daou.kooteam.model.RelationShip;
import com.daou.kooteam.model.User;
import com.daou.kooteam.repository.FollowRepository;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public ResponseDTO<?> followUserWithPhoneNumber(String userId, UserPhoneNumberDTO phoneNumber){
        // user1이 user2를 팔로잉한다.
        User user2 = userRepository.findByPhone_number(phoneNumber.getPhoneNumber()).orElseThrow(() -> {
            throw new FollowerNotFoundException();
        });

        // 만들어둔 followUser 메소드 재사용
        return followUser(userId, user2.getUser_id());
    }

    public ResponseDTO<?> followUser(String userId, String user2){
        User following = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        User follower = userRepository.findById(user2).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Follow follow = followRepository.findByUser1AndUser2(following, follower);

        if(follow == null){
             follow = followRepository.save(Follow.builder()
                    .user1(following)
                    .user2(follower)
                    .relationShip(RelationShip.FRIEND)
                    .build());
        }else{
            if(follow.getRelationShip().equals(RelationShip.FRIEND)){
                follow.setRelationShip(RelationShip.BLOCK);
            }else{
                follow.setRelationShip(RelationShip.FRIEND);
            }
        }

        return new ResponseDTO<>(HttpStatus.OK.value(), follow);
    }

    public ResponseDTO<?> followUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        List<FollowResDTO> follows = followRepository.findAllByUser1(user)
                .stream().map(FollowResDTO::of)
                .collect(Collectors.toList());

        return new ResponseDTO<>(HttpStatus.OK.value(), follows);
    }
}
