package com.example.accounting.service;

import com.example.accounting.dto.CreateAccountRequest;
import com.example.accounting.model.Account;
import com.example.accounting.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account createAccount(CreateAccountRequest req) {
        Account acc = new Account();
        acc.setAccountNumber(req.getAccountNumber());
        acc.setName(req.getName());
        acc.setBalance(BigDecimal.ZERO);
        return repo.save(acc);
    }

    public List<Account> getAllAccounts() {
        return repo.findAll();
    }

    public BigDecimal getBalance(String accountNumber) {
        return repo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"))
                .getBalance();
    }
}
