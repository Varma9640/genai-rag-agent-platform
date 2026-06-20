package com.varma.api.rest.impl;

import com.varma.api.rest.ConversationResource;
import com.varma.domain.service.ConversationManagementService;
import com.varma.model.ConversationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConversationResourceImpl implements ConversationResource {
    private final ConversationManagementService conversationManagementService;

    @Override
    public ResponseEntity<List<ConversationResponse>> getConversations() {
        return ResponseEntity.ok(conversationManagementService.getConversations());
    }

    @Override
    public ResponseEntity<ConversationResponse> getConversation(Long id) {
        return ResponseEntity.ok(conversationManagementService.getConversation(id));
    }

    @Override
    public ResponseEntity<Void> deleteConversation(Long id) {
        conversationManagementService.deleteConversation(id);
        return ResponseEntity.noContent().build();
    }
}
