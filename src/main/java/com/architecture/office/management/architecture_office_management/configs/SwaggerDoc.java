package com.architecture.office.management.architecture_office_management.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDoc {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Architecture Office Management")
                        .description("API para gerenciamento de escritório de arquitetura")
                        .contact(new Contact()
                                .name("Carla Hentschel")
                                .email("carlarhentschel@gmail.com")));
    }
}
