package com.varma.api.rest.impl;

import com.varma.api.ChatResource;
import com.varma.domain.service.ChatService;
import com.varma.model.ChatRequest;
import com.varma.model.ChatResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatResourceImpl implements ChatResource {
    private final ChatService chatService;

    @Override
    public ResponseEntity<ChatResponse> query(ChatRequest request) {
        return ResponseEntity.ok(chatService.query(request));
    }
}
