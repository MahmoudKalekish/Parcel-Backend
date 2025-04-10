package com.parceldelivery.parcel_backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Parcel Delivery API")
                        .version("1.0.0")
                        .description("Backend API for managing parcels and clients."));
    }
}