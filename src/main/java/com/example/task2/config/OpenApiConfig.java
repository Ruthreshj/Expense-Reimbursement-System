package com.example.task2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_SCHEME = "bearerAuth";

    @Bean
    public OpenAPI expenseReimbursementOpenAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(BEARER_SCHEME))
            .components(new Components().addSecuritySchemes(
                BEARER_SCHEME,
                new SecurityScheme()
                    .name(BEARER_SCHEME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
                .info(new Info()
                        .title("Expense Reimbursement API")
                        .description("APIs for employee signup/login and expense reimbursement workflows")
                        .version("v1")
                        .contact(new Contact().name("Expense Reimbursement Team"))
                        .license(new License().name("Internal Use")));
    }
}
