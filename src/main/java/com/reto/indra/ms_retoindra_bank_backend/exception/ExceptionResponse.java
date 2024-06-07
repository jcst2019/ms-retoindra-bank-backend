package com.reto.indra.ms_retoindra_bank_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExceptionResponse {

	private LocalDateTime fecha;
	private String mensaje;
	private String detalles;

}
