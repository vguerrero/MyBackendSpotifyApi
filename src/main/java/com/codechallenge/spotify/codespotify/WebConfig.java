package com.codechallenge.spotify.codespotify;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {
    @Value("${spotify.api.baseurl}")
    private String baseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(baseUrl).build();
    }

}
