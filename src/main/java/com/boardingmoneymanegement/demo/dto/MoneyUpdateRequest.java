package com.boardingmoneymanegement.demo.dto;

import com.boardingmoneymanegement.demo.enums.TransactionType;
import lombok.Data;

@Data
public class MoneyUpdateRequest {

    private Long userId;
    private Double amount;
    private String description;
    private TransactionType transactionType;
}
