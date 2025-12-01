package com.example.accounting.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LedgerEntry {
    private String accountNumber;
    private BigDecimal balance;
    private String summary;
}
