package com.example.accounting.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.accounting.dto.PostTransactionRequest;
import com.example.accounting.model.Account;
import com.example.accounting.model.Transaction;
import com.example.accounting.repository.AccountRepository;
import com.example.accounting.repository.TransactionRepository;
import java.math.BigDecimal;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    
    @InjectMocks
    private TransactionService transactionService;
@Mock
    private AccountRepository accountRepo;
@Mock
    private TransactionRepository transactionRepo;
    @BeforeEach
    public void setUp() {
        this.accountRepo = mock(AccountRepository.class);
        this.transactionRepo = mock(TransactionRepository.class);
    }

    // Happy path (positive)
    @Test
    public void testPostTransactionHappyPath() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(500));

        Transaction tx = transactionService.postTransaction(req);

        assertNotNull(tx);
        assertEquals("TX-" + System.currentTimeMillis(), tx.getReference());
        assertEquals(acc, tx.getAccount());
        assertEquals(BigDecimal.valueOf(500), tx.getAmount());
    }

    // Negative path (validation/error handling)
    @Test
    public void testPostTransactionNegativePath() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(600)); // Insufficient balance

        Transaction tx = transactionService.postTransaction(req);

        assertNull(tx);
        verify(accountRepo, times(1)).findByAccountNumber(anyString());
    }

    // Boundary values (e.g., 0, negative, large values if relevant)
    @Test
    public void testPostTransactionBoundaryValues() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(0)); // Zero amount

        Transaction tx = transactionService.postTransaction(req);

        assertNull(tx);
        verify(accountRepo, times(1)).findByAccountNumber(anyString());
    }

    @Test
    public void testPostTransactionNegativeBalance() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(500)); // Insufficient balance

        Transaction tx = transactionService.postTransaction(req);

        assertNull(tx);
        verify(accountRepo, times(1)).findByAccountNumber(anyString());
    }

    @Test
    public void testPostTransactionNegativeAmount() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(-50)); // Negative amount

        Transaction tx = transactionService.postTransaction(req);

        assertNull(tx);
        verify(accountRepo, times(1)).findByAccountNumber(anyString());
    }

    @Test
    public void testPostTransactionBoundaryValuesLarge() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(null);
        req.setAmount(new BigDecimal(100000)); // Large value

        Transaction tx = transactionService.postTransaction(req);

        assertNotNull(tx);
        assertEquals("TX-" + System.currentTimeMillis(), tx.getReference());
        assertEquals(acc, tx.getAccount());
        assertEquals(BigDecimal.valueOf(100000), tx.getAmount());
    }
}