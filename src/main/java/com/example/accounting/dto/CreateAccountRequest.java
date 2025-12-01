package com.example.accounting.dto;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String accountNumber;
    private String name;
}
