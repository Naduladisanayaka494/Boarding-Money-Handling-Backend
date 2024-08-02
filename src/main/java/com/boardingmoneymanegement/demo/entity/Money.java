package com.boardingmoneymanegement.demo.entity;

import com.boardingmoneymanegement.demo.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "money")
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String description;
}



