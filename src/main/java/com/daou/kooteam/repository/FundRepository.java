package com.daou.kooteam.repository;

import com.daou.kooteam.model.Fund;
import com.daou.kooteam.model.Item;
import com.daou.kooteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    public Fund findByUserAndItem(User user, Item item);
}
