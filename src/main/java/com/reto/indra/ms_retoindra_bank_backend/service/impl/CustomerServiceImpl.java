package com.reto.indra.ms_retoindra_bank_backend.service.impl;

import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private WebClient webClientCustomer;

    @Override
    public Mono<Customer> getCustomerByUniqueCode(String uniqueCode) {
        return webClientCustomer.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/findByUniqueCode")
                        .queryParam("uniqueCode", uniqueCode)
                        .build())
                .retrieve()
                .bodyToMono(Customer.class);
    }
}
