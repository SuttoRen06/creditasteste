# Simulador de Crédito

API desenvolvida como parte de um teste técnico para vaga de Engenheiro de Software Pleno.

## 🚀 Tecnologias Utilizadas

- Kotlin
- Spring Boot 3
- Maven
- JUnit 5
- Swagger (OpenAPI 3)
- Jakarta Validation

## 📌 Funcionalidade

Simula um empréstimo com base nos seguintes critérios:

- Valor do empréstimo
- Data de nascimento do cliente
- Prazo de pagamento em meses

A taxa de juros anual varia conforme a idade:
- Até 25 anos: **5% ao ano**
- De 26 a 40 anos: **3% ao ano**
- De 41 a 60 anos: **2% ao ano**
- Acima de 60 anos: **4% ao ano**

Fórmula utilizada para cálculo de parcelas fixas:

```
PMT = PV * r / (1 - (1 + r)^-n)
```

## 🧪 Testes

Foram implementados:

- ✅ Testes unitários da lógica de cálculo (service)
- ✅ Testes de integração do controller
- ✅ Teste de desempenho simples com alta volumetria

Para rodar os testes:
```bash
./mvnw test
```

## 🔄 Executando o projeto

```bash
./mvnw spring-boot:run
```

Acesse o Swagger em:
```
http://localhost:8080/swagger-ui.html
```

## 📁 Estrutura de pastas

```
com.testecreditas.simuladorcredito
├── api            # Controllers, DTOs, handlers
├── application    # UseCases
├── domain         # Regras de negócio
├── service        # Implementações de serviço
└── SimuladorCreditoApplication.kt
```