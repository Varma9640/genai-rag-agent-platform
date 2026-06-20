package com.varma.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LlmFactoryService {
    private final OllamaLlmClientService ollama;
    private final OpenAiLlmClientService openAi;

    @Value("${llm.provider}")
    private String provider;

    public LlmClientService getClient() {
        if ("openai".equalsIgnoreCase(provider)) {
            return openAi;
        }
        return ollama;
    }
}
