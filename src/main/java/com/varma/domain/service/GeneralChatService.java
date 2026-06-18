package com.varma.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralChatService {
    private final OllamaLlmClientService ollamaLlmClientService;

    public String ask(String question) {
        String prompt = """
                You are a helpful AI assistant.

                Question:
                %s

                Answer:
                """
                .formatted(question);
        return ollamaLlmClientService.generateResponse(prompt);
    }
}
