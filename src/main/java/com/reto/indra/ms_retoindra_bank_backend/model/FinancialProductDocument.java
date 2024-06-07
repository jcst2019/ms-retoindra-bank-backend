package com.reto.indra.ms_retoindra_bank_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinancialProductDocument {

    private String id;
    private String uniqueCode;
    private String coinType;
    private int type;
    private String name;
    private Double interestRate; // Solo para SavingsAccount
    private Double creditLimit;  // Solo para CreditCard
    private Double overdraftLimit; // Solo para CheckingAccount
}
