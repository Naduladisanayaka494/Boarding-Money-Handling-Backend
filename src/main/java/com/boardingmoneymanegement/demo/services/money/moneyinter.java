package com.boardingmoneymanegement.demo.services.money;

import com.boardingmoneymanegement.demo.entity.Money;

import java.util.List;

public interface moneyinter {

    List<Money> getMoneyTransactions(Long userId);
    Money spendMoney(Long userId, Double amount, String description);

    List<Money> getAllTransactions();
}
