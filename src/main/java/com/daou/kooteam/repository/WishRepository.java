package com.daou.kooteam.repository;

import com.daou.kooteam.model.Item;
import com.daou.kooteam.model.User;
import com.daou.kooteam.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Integer> {

    public List<Wish> findAllByUser(User user);

    @Query("select w from Wish w inner join Follow f on w.user = f.user2 where f.user1 = :user")
    public List<Wish> exploreWish(User user);

    public Wish findByUserAndItem(User user, Item item);
}
