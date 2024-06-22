package com.reto.indra.ms_retoindra_bank_backend.service.impl;

import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.service.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final WebClient webClientCustomer;

    public CustomerServiceImpl(WebClient webClientCustomer) {
        this.webClientCustomer = webClientCustomer;
    }

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
