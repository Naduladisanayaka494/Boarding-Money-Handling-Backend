package com.boardingmoneymanegement.demo.services.money;

import com.boardingmoneymanegement.demo.entity.Money;
import com.boardingmoneymanegement.demo.entity.User;
import com.boardingmoneymanegement.demo.enums.TransactionType;
import com.boardingmoneymanegement.demo.repository.MoneyRepository;
import com.boardingmoneymanegement.demo.repository.UserRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyService {
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
        return moneyRepository.save(money);
    }

    public List<Money> getMoneyTransactions(Long userId) {
        return moneyRepository.findByUserId(userId);
    }
}
