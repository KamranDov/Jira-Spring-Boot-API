package com.example.jiratest.service;

import com.example.jiratest.domain.JiraPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class JiraAccessService {

    private final RestTemplate restTemplate;

    @Value("${jira.user-name}")
    private String username;
    @Value("${jira.secret}")
    private String secret;
    @Value("${jira.base-url}")
    private String baseUrl;
    @Value("${jira.ticket-url}")
    private String ticketCreationUrl;

    public String accessJira(JiraPayload jiraPayload) {
        ResponseEntity<String> response = restTemplate.exchange(baseUrl.concat(ticketCreationUrl), HttpMethod.POST,
                new HttpEntity<JiraPayload>(jiraPayload, getHeaders()), String.class);
        return response.getBody();
    }
    public HttpHeaders getHeaders() {
        String auth = username + ":" + secret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }
}
