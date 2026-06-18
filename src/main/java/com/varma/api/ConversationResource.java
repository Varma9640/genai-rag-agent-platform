package com.varma.api;

import com.varma.model.ConversationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@Tag(name = "Conversation Resource")
@RequestMapping(value = "/api/v1/conversations", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ConversationResource {
    @Operation(
            summary = "Get Conversations",
            description = "Retrieve all conversations")
    @GetMapping
    ResponseEntity<List<ConversationResponse>>
    getConversations();

    @Operation(
            summary = "Get Conversation",
            description = "Retrieve conversation by id")
    @GetMapping("/{id}")
    ResponseEntity<ConversationResponse>
    getConversation(
            @PathVariable Long id);

    @Operation(
            summary = "Delete Conversation",
            description = "Delete conversation by id")
    @DeleteMapping("/{id}")
    ResponseEntity<Void>
    deleteConversation(
            @PathVariable Long id);
}
