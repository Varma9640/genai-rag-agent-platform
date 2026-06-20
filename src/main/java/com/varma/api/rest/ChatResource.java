package com.varma.api.rest;

import com.varma.api.swagger.ChatSwagger;
import com.varma.model.ChatRequest;
import com.varma.model.ChatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@Tag(name = "Chat Resource")
@ChatSwagger
@RequestMapping(value = "/api/v1/chat", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ChatResource {
    @Operation(summary = "Chat with AI", description = "Generate response using Llama3")
    @PostMapping("/generate")
    ResponseEntity<ChatResponse> query(@Valid @RequestBody ChatRequest request);
}
