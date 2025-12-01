package com.example.accounting.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String name;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;
}
