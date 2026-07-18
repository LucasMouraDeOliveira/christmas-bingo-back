package com.lordkadoc.bingo_back.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Christmas Bingo Back API")
                        .description("REST endpoints for player and bingo grid management.")
                        .version("1.0")
                        .contact(new Contact().name("Christmas Bingo Team").email("support@example.com")));
    }

    @Bean
    public GroupedOpenApi bingoApi() {
        return GroupedOpenApi.builder()
                .group("bingo-back")
                .packagesToScan("com.lordkadoc.bingo_back.player", "com.lordkadoc.bingo_back.bingo_grid.presentation")
                .build();
    }
}
