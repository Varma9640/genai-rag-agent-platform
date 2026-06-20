package com.varma.domain.service;

import com.varma.model.ChatRequest;
import com.varma.model.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final OllamaLlmClientService ollamaLlmClientService;

    public ChatResponse query(ChatRequest request) {
        log.info("Executing AI Chat Request");
        String answer = ollamaLlmClientService.generateResponse(request.getPrompt());

        return ChatResponse.builder().response(answer).build();
    }
}
