# svc-accounting

A simple Spring Boot–based Accounting microservice used for generating test cases using AI agents (TestWeaver).

svc-accounting is a lightweight, modular, and production-ready Spring Boot Accounting Microservice designed to serve as the target application for AI-driven automated test generation. It exposes REST endpoints for account management and financial transactions while maintaining simple, clean business logic that an AI agent—such as TestWeaver—can easily interpret and generate meaningful unit tests, integration tests, and behavior-driven test cases for.

The service includes:

CRUD-style operations for Accounts

Support for Credit and Debit transactions

Built-in validation (e.g., preventing negative balances)

An in-memory H2 database for fast setup and testing

Full Swagger / OpenAPI documentation, enabling interactive API exploration

A well-structured architecture using controllers, services, repositories, and DTOs

This repository acts as the source application code that your AI agent tools will analyze through Git APIs, understand using RAG memory, and generate high-quality automated test cases for—making it ideal for experimentation, test engineering automation, and agent-based development workflows.

## Features
- Account creation
- Post credit/debit transactions
- Balance inquiry
- Swagger UI documentation
- H2 in-memory database
- Clean controller/service/repository architecture

## Run

### Requirements
- JDK 17+
- Maven 3.9+

### Run the app
```bash
mvn spring-boot:run

📄 API Documentation (Swagger UI)

Once the application is running on port 8080, use the following links:

🔗 Swagger UI

http://localhost:8080/swagger-ui.html

🔗 OpenAPI JSON

http://localhost:8080/v3/api-docs

🔗 OpenAPI YAML

http://localhost:8080/v3/api-docs.yaml

🧪 Sample API Usage
Create an Account

POST /accounts

{
  "accountNumber": "ACC1001",
  "name": "John Doe"
}

Get Account List

GET /accounts

Check Balance

GET /accounts/ACC1001/balance

Post a Transaction

POST /transactions

{
  "accountNumber": "ACC1001",
  "type": "CREDIT",
  "amount": 500
}
