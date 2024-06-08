package com.reto.indra.ms_retoindra_bank_backend.service;

import com.reto.indra.ms_retoindra_bank_backend.controller.BankController;
import com.reto.indra.ms_retoindra_bank_backend.exception.ModeloNotFoundException;
import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.model.FinancialProductDocument;
import com.reto.indra.ms_retoindra_bank_backend.model.InformationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    private FinancialProductService financialProductService;

    @Autowired
    private CustomerService customerService;

    public Mono<InformationResponse> getCombinedResponse(String uniqueCode) {
        logger.info("Consumiendo Servicio Customer");
        Mono<Customer> customerMono = customerService.getCustomerByUniqueCode(uniqueCode)
                .switchIfEmpty(Mono.error(new ModeloNotFoundException("Customer not found for unique code: " + uniqueCode)));
        logger.info("Consumiendo Servicio Customer => "+ customerMono.toString());
        logger.info("Consumiendo Servicio financialProducts");
        Mono<List<FinancialProductDocument>> financialProductsMono = financialProductService.getFinancialProductsByUniqueCode(uniqueCode).collectList()
                .filter(list -> !list.isEmpty())
                .switchIfEmpty(Mono.error(new ModeloNotFoundException("Financial products not found for unique code: " + uniqueCode)));
        logger.info("Consumiendo Servicio financialProducts => "+financialProductsMono.toString());
        logger.info("Combinando Flujos");
        return Mono.zip(customerMono, financialProductsMono, (customer, financialProducts) -> {
            InformationResponse combinedResponse = new InformationResponse();
            combinedResponse.setCustomer(customer);
            combinedResponse.setFinancialProducts(financialProducts);
            return combinedResponse;
        });
    }
}