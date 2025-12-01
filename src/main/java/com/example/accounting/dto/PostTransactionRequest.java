package com.example.accounting.dto;

import com.example.accounting.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostTransactionRequest {
    private String accountNumber;
    private TransactionType type;
    private BigDecimal amount;
}
