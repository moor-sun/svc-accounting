# svc-accounting

A simple Spring Boot–based Accounting microservice used for generating test cases using AI agents (TestWeaver).

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
