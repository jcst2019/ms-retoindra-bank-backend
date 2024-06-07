package com.reto.indra.ms_retoindra_bank_backend.exception;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResponseExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);
	// Este método maneja todas las excepciones no definidas específicamente
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Mono<ResponseEntity<ExceptionResponse>> manejarTodasExcepciones(Exception ex, ServerWebExchange exchange) {
		String errorMessage = "Uncontrolled error, contact the systems area.";
		ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), errorMessage,
				exchange.getRequest().getURI().toString());
		logger.error("Error Generico =>", ex);
		return Mono.just(new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler({InvalidTypeIdException.class, IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<ResponseEntity<ExceptionResponse>> handleInvalidTypeIdAndIllegalState(Exception ex, ServerWebExchange exchange) {
		logger.error("Exception in createFinancialProduct", ex);
		String errorMessage = "Exception: " + ex.getMessage();
		ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), errorMessage,
				exchange.getRequest().getURI().toString());
		return Mono.just(new ResponseEntity<>(er, HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(ModeloNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Mono<ResponseEntity<ExceptionResponse>> manejarModeloException(ModeloNotFoundException ex, ServerWebExchange exchange) {
		ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
				exchange.getRequest().getURI().toString());
		logger.error("ModeloNotFoundException in createFinancialProduct", ex);
		return Mono.just(new ResponseEntity<>(er, HttpStatus.NOT_FOUND));
	}
}
