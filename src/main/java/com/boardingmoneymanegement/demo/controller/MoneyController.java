package com.boardingmoneymanegement.demo.controller;

import com.boardingmoneymanegement.demo.dto.MoneyRequest;
import com.boardingmoneymanegement.demo.entity.Money;
import com.boardingmoneymanegement.demo.services.money.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money")
@RequiredArgsConstructor
public class MoneyController {
    private final MoneyService moneyService;

    @PostMapping("/add")
    public Money addMoney(@RequestBody MoneyRequest request) {
        return moneyService.addMoney(request.getUserId(), request.getAmount(), request.getDescription());
    }

    @PostMapping("/spend")
    public Money spendMoney(@RequestBody MoneyRequest request) {
        return moneyService.spendMoney(request.getUserId(), request.getAmount(), request.getDescription());
    }

    @GetMapping("/transactions")
    public List<Money> getMoneyTransactions(@RequestParam Long userId) {
        return moneyService.getMoneyTransactions(userId);
    }
}
