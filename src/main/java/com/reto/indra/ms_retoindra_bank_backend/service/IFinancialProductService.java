package com.reto.indra.ms_retoindra_bank_backend.service;

import com.reto.indra.ms_retoindra_bank_backend.model.FinancialProductDocument;
import reactor.core.publisher.Flux;

public interface IFinancialProductService {

    Flux<FinancialProductDocument> getFinancialProductsByUniqueCode(String uniqueCode);

}
