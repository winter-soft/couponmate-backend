package com.daou.kooteam.repository;

import com.daou.kooteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("select count(u.user_id) > 0 from User u where u.platform_type = :platform_type and u.platform_id = :platform_id and u.phone_number = :phone_number")
    Boolean existsByPlatform_typeAndPlatform_idAndPhone_number(String platform_type, String platform_id, String phone_number);

    @Query("select u from User u where u.platform_id = :platform_id and u.platform_type = :platform_type")
    Optional<User> findByPlatform_idAndPlatform_type(String platform_id, String platform_type);

    Optional<User> findByToken(String token);

    @Query("select u from User u where u.phone_number = :phone_number")
    Optional<User> findByPhone_number(String phone_number);
}
