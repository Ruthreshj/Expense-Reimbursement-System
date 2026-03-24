package com.example.task2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseReimbursementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Reimbursement API")
                        .description("APIs for employee signup/login and expense reimbursement workflows")
                        .version("v1")
                        .contact(new Contact().name("Expense Reimbursement Team"))
                        .license(new License().name("Internal Use")));
    }
}
