package com.daou.kooteam.repository;

import com.daou.kooteam.model.Follow;
import com.daou.kooteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

    public Follow findByUser1AndUser2(User user1, User user2);

    public List<Follow> findAllByUser1(User user1);
}
