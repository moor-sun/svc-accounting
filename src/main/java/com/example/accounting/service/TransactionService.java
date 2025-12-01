package com.example.accounting.service;

import com.example.accounting.dto.PostTransactionRequest;
import com.example.accounting.model.*;
import com.example.accounting.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;

    public TransactionService(AccountRepository accountRepo, TransactionRepository transactionRepo) {
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    public Transaction postTransaction(PostTransactionRequest req) {
        Account acc = accountRepo.findByAccountNumber(req.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal newBalance;

        if (req.getType() == TransactionType.CREDIT) {
            newBalance = acc.getBalance().add(req.getAmount());
        } else {
            newBalance = acc.getBalance().subtract(req.getAmount());
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Insufficient balance");
            }
        }

        acc.setBalance(newBalance);
        accountRepo.save(acc);

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setAmount(req.getAmount());
        tx.setReference("TX-" + System.currentTimeMillis());
        tx.setType(req.getType());

        return transactionRepo.save(tx);
    }
}
