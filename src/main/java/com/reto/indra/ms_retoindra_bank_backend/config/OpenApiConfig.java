package com.reto.indra.ms_retoindra_bank_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tu API Documentada con OpenAPI 3.0")
                        .version("1.0")
                        .description("Esta es la documentación de tu API")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tuemail@ejemplo.com")));
    }
}