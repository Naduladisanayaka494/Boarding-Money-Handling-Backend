package com.boardingmoneymanegement.demo.controller;

import com.boardingmoneymanegement.demo.dto.MoneyRequest;
import com.boardingmoneymanegement.demo.dto.MoneyUpdateRequest;
import com.boardingmoneymanegement.demo.dto.UserDto;
import com.boardingmoneymanegement.demo.entity.Money;
import com.boardingmoneymanegement.demo.services.auth.AuthServiceImpl;
import com.boardingmoneymanegement.demo.services.money.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money")
@RequiredArgsConstructor
public class MoneyController {
    private final MoneyService moneyService;

    private final AuthServiceImpl authService;

    @PostMapping("/add")
    public Money addMoney(@RequestBody MoneyRequest request) {
        return moneyService.addMoney(request.getUserId(), request.getAmount(), request.getDescription());
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Money> getTransactionById(@PathVariable Long transactionId) {
        return moneyService.getTransactionById(transactionId)
                .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/spend")
    public Money spendMoney(@RequestBody MoneyRequest request) {
        return moneyService.spendMoney(request.getUserId(), request.getAmount(), request.getDescription());
    }

    @GetMapping("/transactions")
    public List<Money> getMoneyTransactions(@RequestParam Long userId) {
        return moneyService.getMoneyTransactions(userId);
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDto>> getAllStudents() {
        List<UserDto> students = authService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/all-transactions")
    public List<Money> getAllTransactions() {
        return moneyService.getAllTransactions();
    }


    @PutMapping("/update/{transactionId}")
    public ResponseEntity<Money> updateTransaction(
            @PathVariable Long transactionId,
            @RequestBody MoneyUpdateRequest request) {

        Money updatedTransaction = moneyService.updateTransaction(
                transactionId,
                request.getUserId(),
                request.getAmount(),
                request.getDescription(),
                request.getTransactionType());

        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        try {
            moneyService.deleteTransaction(transactionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }

}
