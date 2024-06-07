package com.reto.indra.ms_retoindra_bank_backend.service;

import com.reto.indra.ms_retoindra_bank_backend.model.FinancialProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class FinancialProductService {

    @Autowired
    private WebClient webClientProduct;

    public Flux<FinancialProductDocument> getFinancialProductsByUniqueCode(String uniqueCode) {
        return webClientProduct.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/findByUniqueCode")
                        .queryParam("uniqueCode", uniqueCode)
                        .build())
                .retrieve()
                .bodyToFlux(FinancialProductDocument.class);
    }
}