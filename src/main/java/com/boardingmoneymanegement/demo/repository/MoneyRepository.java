package com.boardingmoneymanegement.demo.repository;


import com.boardingmoneymanegement.demo.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {

    List<Money> findByUserId(Long userId);
}
