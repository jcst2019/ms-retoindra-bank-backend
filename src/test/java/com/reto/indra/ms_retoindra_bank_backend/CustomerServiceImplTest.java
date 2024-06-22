package com.reto.indra.ms_retoindra_bank_backend;

import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    private WebClient webClientCustomer;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer mockCustomer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCustomer = new Customer(
                "12345678",
                "codigoValido",
                "Juan Carlos",
                "Solar",
                "87654321",
                25,
                "456 Elm St",
                "9876543210",
                "jane@example.com",
                new Date()
        );
    }

    @Test
    public void getCustomerByUniqueCode_success() {
        Mono<Customer> customerMono = Mono.just(mockCustomer);

        when(webClientCustomer.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Customer.class)).thenReturn(customerMono);

        Mono<Customer> result = customerService.getCustomerByUniqueCode("codigoValido");

        StepVerifier.create(result)
                .expectNextMatches(customer -> customer.getId().equals("12345678") &&
                        customer.getUniqueCode().equals("codigoValido") &&
                        customer.getName().equals("Juan Carlos") &&
                        customer.getLastName().equals("Solar") &&
                        customer.getDni().equals("87654321") &&
                        customer.getAge() == 25 &&
                        customer.getAddress().equals("456 Elm St") &&
                        customer.getPhoneNumber().equals("9876543210") &&
                        customer.getEmail().equals("jane@example.com"))
                .verifyComplete();
    }

    @Test
    public void getCustomerByUniqueCode_notFound() {
        when(webClientCustomer.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Customer.class)).thenReturn(Mono.error(new WebClientResponseException(404, "Not Found", null, null, null)));

        Mono<Customer> result = customerService.getCustomerByUniqueCode("codigoInvalido");

        StepVerifier.create(result)
                .expectError(WebClientResponseException.class)
                .verify();
    }
}
