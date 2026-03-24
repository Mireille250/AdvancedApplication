package org.example.advancedapplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("E-commerce API").
                        version("1.0.0").
                        description("THIS IS THE ECOMMERCE API THAT HAS MANY FEATURES LIKE CUSTOMER,PRODUCT AND ORDER SERVICES "));
    }
}
