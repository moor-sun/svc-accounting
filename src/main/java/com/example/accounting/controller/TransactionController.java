package com.example.accounting.controller;

import com.example.accounting.dto.PostTransactionRequest;
import com.example.accounting.model.Transaction;
import com.example.accounting.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions API")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> postTransaction(@RequestBody PostTransactionRequest request) {
        return ResponseEntity.ok(service.postTransaction(request));
    }
}
