package com.reto.indra.ms_retoindra_bank_backend;

import com.reto.indra.ms_retoindra_bank_backend.controller.BankController;
import com.reto.indra.ms_retoindra_bank_backend.model.Customer;
import com.reto.indra.ms_retoindra_bank_backend.model.FinancialProductDocument;
import com.reto.indra.ms_retoindra_bank_backend.model.InformationResponse;
import com.reto.indra.ms_retoindra_bank_backend.service.IBankService;
import com.reto.indra.ms_retoindra_bank_backend.service.ICustomerService;
import com.reto.indra.ms_retoindra_bank_backend.service.IFinancialProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;

@SpringBootTest
class MsRetoindraBankBackendApplicationTests {

	@Mock
	private IFinancialProductService financialProductService;

	@Mock
	private ICustomerService customerService;

	@Mock
	private IBankService bankService;

	@InjectMocks
	private BankController bankController;

	private Customer juanCarlos;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		juanCarlos = new Customer(
				"1",
				"QeCVfZKCyo+UFXLvrwmpVw==",
				"Juan Carlos",
				"Solar Torres",
				"43032393",
				39,
				"Piura",
				"1234567890",
				"juan@example.com",
				new Date()
		);
	}

	@Test
	public void testProductFindByUniqueCode() {
		String uniqueCode = "QeCVfZKCyo+UFXLvrwmpVw==";
		FinancialProductDocument checkingAccount = new FinancialProductDocument(null, uniqueCode, "USD", 3, "Checking Account", null, null, 0.0);
		FinancialProductDocument savingsAccount = new FinancialProductDocument(null, uniqueCode, "USD", 1, "Savings Account", 1.5, null, null);
		FinancialProductDocument creditCard = new FinancialProductDocument(null, uniqueCode, "USD", 2, "Credit Card", null, 0.0, null);
		when(financialProductService.getFinancialProductsByUniqueCode(uniqueCode)).thenReturn(Flux.just(checkingAccount, savingsAccount, creditCard));

		ResponseEntity<Flux<FinancialProductDocument>> response = bankController.productFindByUniqueCode(uniqueCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		StepVerifier.create(response.getBody())
				.expectNext(checkingAccount)
				.expectNext(savingsAccount)
				.expectNext(creditCard)
				.verifyComplete();
	}

	@Test
	public void testCustomerFindByUniqueCode() {
		String uniqueCode = "QeCVfZKCyo+UFXLvrwmpVw==";
		when(customerService.getCustomerByUniqueCode(uniqueCode)).thenReturn(Mono.just(juanCarlos));

		ResponseEntity<Mono<Customer>> response = bankController.customerFindByUniqueCode(uniqueCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		StepVerifier.create(response.getBody())
				.expectNext(juanCarlos)
				.verifyComplete();
	}

	@Test
	public void testInformationFindByUniqueCode() {
		String uniqueCode = "QeCVfZKCyo+UFXLvrwmpVw==";
		FinancialProductDocument checkingAccount = new FinancialProductDocument(null, uniqueCode, "USD", 3, "Checking Account", null, null, 0.0);
		FinancialProductDocument savingsAccount = new FinancialProductDocument(null, uniqueCode, "USD", 1, "Savings Account", 1.5, null, null);
		FinancialProductDocument creditCard = new FinancialProductDocument(null, uniqueCode, "USD", 2, "Credit Card", null, 0.0, null);
		InformationResponse informationResponse = new InformationResponse(juanCarlos, Arrays.asList(checkingAccount, savingsAccount, creditCard));
		when(bankService.getCombinedResponse(uniqueCode)).thenReturn(Mono.just(informationResponse));

		ResponseEntity<Mono<InformationResponse>> response = bankController.informationFindByUniqueCode(uniqueCode);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		StepVerifier.create(response.getBody())
				.expectNext(informationResponse)
				.verifyComplete();
	}
}
