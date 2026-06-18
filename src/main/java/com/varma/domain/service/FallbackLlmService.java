package com.varma.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FallbackLlmService {
    private final LlmFactoryService llmFactoryService;
    private final OllamaLlmClientService ollamaLlmClientService;

    public String generateResponse(String prompt) {

        try {

            return llmFactoryService
                    .getClient()
                    .generateResponse(prompt);

        } catch (Exception exception) {

            log.error(
                    "Primary LLM Failed. Falling back to Ollama",
                    exception);

            return ollamaLlmClientService
                    .generateResponse(prompt);
        }
    }
}
