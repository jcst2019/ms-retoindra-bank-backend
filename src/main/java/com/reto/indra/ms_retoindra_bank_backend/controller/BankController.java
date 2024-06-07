package com.reto.indra.ms_retoindra_bank_backend.controller;

import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.model.FinancialProductDocument;
import com.reto.indra.ms_retoindra_bank_backend.service.CustomerService;
import com.reto.indra.ms_retoindra_bank_backend.service.FinancialProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bank")
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    private FinancialProductService financialProductService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value="/productFindByUniqueCode")
    public ResponseEntity<Flux<FinancialProductDocument>> productFindByUniqueCode(@RequestParam("uniqueCode") String encodedUniqueCode){
        encodedUniqueCode = encodedUniqueCode.replace(" ", "+");
        logger.info("encodedUniqueCode: {}", encodedUniqueCode);
        Flux<FinancialProductDocument> financialProducts = financialProductService.getFinancialProductsByUniqueCode(encodedUniqueCode);
        return new ResponseEntity<>(financialProducts, HttpStatus.OK);
    }
    @GetMapping(value="/customerFindByUniqueCode")
    public ResponseEntity<Mono<Customer>> customerFindByUniqueCode(@RequestParam("uniqueCode") String encodedUniqueCode){
        encodedUniqueCode = encodedUniqueCode.replace(" ", "+");
        logger.info("encodedUniqueCode: {}", encodedUniqueCode);
        Mono<Customer> customer = customerService.getCustomerByUniqueCode(encodedUniqueCode);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
