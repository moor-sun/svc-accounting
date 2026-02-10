package com.example.accounting.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.accounting.dto.PostTransactionRequest;
import com.example.accounting.model.*;
import com.example.accounting.repository.AccountRepository;
import com.example.accounting.repository.TransactionRepository;
import java.math.BigDecimal;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;
public class TransactionServiceTest {
@Mock
    private AccountRepository accountRepo;
@Mock
    private TransactionRepository transactionRepo;
    private TransactionService service;

    @BeforeEach
    public void setUp() {
        service = new TransactionService(accountRepo, transactionRepo);
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
        req.setType(TransactionType.CREDIT);
        req.setAmount(new BigDecimal(500));

        Transaction tx = service.postTransaction(req);

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
        req.setType(TransactionType.CREDIT);
        req.setAmount(new BigDecimal(600)); // Insufficient balance

        assertThrows(RuntimeException.class, () -> service.postTransaction(req));
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
        req.setType(TransactionType.CREDIT);
        req.setAmount(new BigDecimal(0)); // Zero amount

        assertThrows(RuntimeException.class, () -> service.postTransaction(req));
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
        req.setType(TransactionType.CREDIT);
        req.setAmount(new BigDecimal(-50)); // Negative amount

        assertThrows(RuntimeException.class, () -> service.postTransaction(req));
    }

    @Test
    public void testPostTransactionLargeAmount() {
        Account acc = new Account();
        acc.setAccountNumber("ACC1001");
        acc.setName("John Doe");
        acc.setBalance(BigDecimal.ZERO);

        when(accountRepo.findByAccountNumber(anyString())).thenReturn(Optional.of(acc));

        PostTransactionRequest req = new PostTransactionRequest();
        req.setAccountNumber("ACC1001");
        req.setType(TransactionType.CREDIT);
        req.setAmount(new BigDecimal(1000000)); // Large amount

        Transaction tx = service.postTransaction(req);

        assertNotNull(tx);
        assertEquals("TX-" + System.currentTimeMillis(), tx.getReference());
        assertEquals(acc, tx.getAccount());
        assertEquals(BigDecimal.valueOf(1000000), tx.getAmount());
    }
}