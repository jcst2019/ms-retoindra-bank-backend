package com.reto.indra.ms_retoindra_bank_backend.service;

import com.reto.indra.ms_retoindra_bank_backend.model.InformationResponse;
import reactor.core.publisher.Mono;

public interface IBankService {

    Mono<InformationResponse> getCombinedResponse(String uniqueCode);

}
