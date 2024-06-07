package com.reto.indra.ms_retoindra_bank_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${external.ms-retoindra-customer-backend.url}")
    private String serviceCustomerBaseUrl;

    @Value("${external.ms-retoindra-product-financiero-backend.url}")
    private String serviceProductBaseUrl;

    @Bean
    public WebClient webClientCustomer() {
        return WebClient.builder()
                .baseUrl(serviceCustomerBaseUrl)
                .build();
    }

    @Bean
    public WebClient webClientProduct() {
        return WebClient.builder()
                .baseUrl(serviceProductBaseUrl)
                .build();
    }
}
