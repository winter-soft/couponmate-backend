package com.daou.kooteam.service;

import com.daou.kooteam.config.security.jwt.TokenProvider;
import com.daou.kooteam.dto.user.UserDTO;
import com.daou.kooteam.dto.user.UserTokenDTO;
import com.daou.kooteam.exception.user.UserDuplicatedException;
import com.daou.kooteam.exception.user.UserInformationEmptyException;
import com.daou.kooteam.exception.user.UserNotFoundException;
import com.daou.kooteam.exception.user.UserTokenNotFoundException;
import com.daou.kooteam.model.User;
import com.daou.kooteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.daou.kooteam.dto.user.UserDTO.entityToDTO;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public User create(final User userEntity){
        if(userEntity == null || userEntity.getPlatform_id() == null || userEntity.getPhone_number() == null){
            throw new UserInformationEmptyException();
        }

        if(userRepository.existsByPlatform_typeAndPlatform_idAndPhone_number(
                userEntity.getPlatform_type(),
                userEntity.getPlatform_id(),
                userEntity.getPhone_number())){
            throw new UserDuplicatedException(); // 유저 중복 Exception
        }

        return userRepository.save(userEntity);
    }

    public UserDTO findById(final String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        return entityToDTO(user);
    }

    @Transactional
    public UserDTO getByCredentials(final String platformType, final String platformId){
        User originalUser = userRepository.findByPlatform_idAndPlatform_type(platformId, platformType)
                .orElseThrow(()->{
                    throw new UserNotFoundException();
                });
        UserDTO userDTO = entityToDTO(originalUser);
        final String token = tokenProvider.create(originalUser);

        originalUser.setToken(token);

        userDTO.setToken(token);
        return userDTO;
    }

    // 가장 최근에 재발급받은 토큰이였을 경우, 재발급을 해주고 그렇지 않은경우는 에러를 발생시킨다.
    @Transactional
    public UserTokenDTO refreshToken(String token){
        User user = userRepository.findByToken(token).orElseThrow(() -> {
            throw new UserTokenNotFoundException();
        });
        token = tokenProvider.create(user); // 토큰 재발급
        user.setToken(token);

        return UserTokenDTO.builder()
                .token(token)
                .build();
    }
}
