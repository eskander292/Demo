package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI itemApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Items API")
                        .description("CRUD API for managing items (in-memory repository).")
                        .version("v1")
                        .contact(new Contact().name("Your Name").email("you@example.com"))
                        .license(new License().name("Apache 2.0"))
                );
    }
}