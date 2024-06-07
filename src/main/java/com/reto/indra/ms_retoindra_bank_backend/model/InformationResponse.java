package com.reto.indra.ms_retoindra_bank_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class InformationResponse {
    private Customer customer;
    private List<FinancialProductDocument> financialProducts;
}
