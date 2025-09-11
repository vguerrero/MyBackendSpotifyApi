package com.codechallenge.spotify.codespotify.service;

import com.codechallenge.spotify.codespotify.dto.SpotifyTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class SpotifyTokenService {

    private final WebClient webClient;

    @Value("${spotify.account.baseurl}")
    private String accountBaseUrl;

    @Value("${spotify.api.client-id}")
    private String clientId;

    @Value("${spotify.api.client-secret}")
    private String clientSecret;

    @Value("${spotify.api.expiryTime}")
    private long expiryTimeSec;


    private String accessToken;


    public SpotifyTokenService(WebClient webClient) {
        this.webClient = webClient;
    }

    public synchronized String getAccessToken() {
        long now = System.currentTimeMillis();

        // token does not exist or is expired
        if (accessToken == null || now >= expiryTimeSec) {
            fetchNewToken();
        }

        return accessToken;
    }

    private void fetchNewToken() {
        String credentials = clientId + ":" + clientSecret;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        var response = webClient.post()
                .uri(accountBaseUrl)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .bodyToMono(SpotifyTokenResponse.class)
                .block();

        if (response != null) {
            this.accessToken = response.getAccess_token();
            this.expiryTimeSec = (System.currentTimeMillis() / 1000) + response.getExpires_in() - 5;
        }
    }
}
