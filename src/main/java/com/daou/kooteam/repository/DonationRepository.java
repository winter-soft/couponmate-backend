package com.daou.kooteam.repository;

import com.daou.kooteam.model.Donate;
import com.daou.kooteam.model.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donate, Integer> {

    public List<Donate> findByFund(Fund fund);
}
