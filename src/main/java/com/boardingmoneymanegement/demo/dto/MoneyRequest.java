package com.boardingmoneymanegement.demo.dto;



import lombok.Data;

@Data
public class MoneyRequest {
    private Long userId;
    private Double amount;
    private String description;
}

