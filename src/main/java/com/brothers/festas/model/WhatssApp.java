package com.brothers.festas.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class WhatssApp {

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.api.token}")
    private String whatsappApiToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarContratoViaWhatsapp(String numeroCliente, byte[] pdf, String nomeArquivo) {
        String pdfBase64 = Base64.getEncoder().encodeToString(pdf);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(whatsappApiToken);

        Map<String, Object> body = new HashMap<>();
        body.put("to", numeroCliente);
        body.put("type", "document");

        Map<String, Object> document = new HashMap<>();
        document.put("filename", nomeArquivo);
        document.put("document", pdfBase64);

        body.put("document", document);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity(whatsappApiUrl + "/send-document", request, String.class);
    }
}

