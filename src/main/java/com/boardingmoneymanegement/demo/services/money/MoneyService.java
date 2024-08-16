package com.boardingmoneymanegement.demo.services.money;

import com.boardingmoneymanegement.demo.entity.Money;
import com.boardingmoneymanegement.demo.entity.User;
import com.boardingmoneymanegement.demo.enums.TransactionType;
import com.boardingmoneymanegement.demo.repository.MoneyRepository;
import com.boardingmoneymanegement.demo.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoneyService implements moneyinter {
    private final MoneyRepository moneyRepository;
    private final UserRepostory userRepository;

    @Transactional
    public Money addMoney(Long userId, Double amount, String description) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTotalMoney(user.getTotalMoney() + amount);  // Update total money
        userRepository.save(user);  // Save user with updated total money

        Money money = new Money();
        money.setUser(user);
        money.setAmount(amount);
        money.setTransactionType(TransactionType.ADD_MONEY);
        money.setDescription(description);
        money.setTransactionDateTime(LocalDateTime.now());  // Set current date and time
        return moneyRepository.save(money);
    }

    @Transactional
    public Money spendMoney(Long userId, Double amount, String description) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTotalMoney() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        user.setTotalMoney(user.getTotalMoney() - amount);  // Update total money
        userRepository.save(user);

        Money money = new Money();
        money.setUser(user);
        money.setAmount(amount);
        money.setTransactionType(TransactionType.SPEND_MONEY);
        money.setDescription(description);
        money.setTransactionDateTime(LocalDateTime.now());  // Set current date and time
        return moneyRepository.save(money);
    }

    public List<Money> getMoneyTransactions(Long userId) {
        return moneyRepository.findByUserId(userId);
    }

    public List<Money> getAllTransactions() {  // Add this method
        return moneyRepository.findAll();
    }


    @Transactional
    public Money updateTransaction(Long transactionId, Long userId, Double amount, String description, TransactionType transactionType) {
        Money transaction = moneyRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Revert the previous transaction amount from the user's total money
        if (transaction.getTransactionType() == TransactionType.ADD_MONEY) {
            user.setTotalMoney(user.getTotalMoney() - transaction.getAmount());
        } else if (transaction.getTransactionType() == TransactionType.SPEND_MONEY) {
            user.setTotalMoney(user.getTotalMoney() + transaction.getAmount());
        }

        // Update the transaction details
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDateTime(LocalDateTime.now());

        // Apply the new transaction amount to the user's total money
        if (transactionType == TransactionType.ADD_MONEY) {
            user.setTotalMoney(user.getTotalMoney() + amount);
        } else if (transactionType == TransactionType.SPEND_MONEY) {
            if (user.getTotalMoney() < amount) {
                throw new RuntimeException("Insufficient funds");
            }
            user.setTotalMoney(user.getTotalMoney() - amount);
        }

        userRepository.save(user);  // Save the updated user
        return moneyRepository.save(transaction);  // Save the updated transaction
    }

    public Optional<Money> getTransactionById(Long transactionId) {
        return moneyRepository.findById(transactionId);
    }


}
