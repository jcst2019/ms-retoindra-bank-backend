package com.reto.indra.ms_retoindra_bank_backend.service;

import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<Customer> getCustomerByUniqueCode(String uniqueCode);
}
