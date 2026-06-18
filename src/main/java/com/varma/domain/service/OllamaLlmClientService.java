package com.varma.domain.service;

import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaLlmClientService implements LlmClientService{
    private final OllamaChatModel ollamaChatModel;

    public String generateResponse(String prompt) {
        log.info("Invoking Ollama Llama3");
        return ollamaChatModel.generate(prompt);
    }
}
