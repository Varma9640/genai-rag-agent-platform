package com.varma.domain.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenAiLlmClientServiceTest {

    private final OpenAiLlmClientService service = new OpenAiLlmClientService();

    @Test
    void testGenerateResponse() {

        String result = service.generateResponse("Hello");
        assertEquals("OpenAI Response", result);
    }
}