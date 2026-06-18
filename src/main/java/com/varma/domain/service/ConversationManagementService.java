package com.varma.domain.service;

import com.varma.domain.entity.ChatSession;
import com.varma.domain.repository.ChatSessionRepository;
import com.varma.model.ConversationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationManagementService {
    private final ChatSessionRepository repository;

    public List<ConversationResponse> getConversations() {

        return repository.findAll()
                .stream()
                .map(session ->
                        ConversationResponse.builder()
                                .id(session.getId())
                                .question(session.getQuestion())
                                .answer(session.getAnswer())
                                .createdDate(session.getCreatedDate())
                                .build())
                .toList();
    }

    public ConversationResponse getConversation(
            Long id) {

        ChatSession session =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Conversation Not Found"));

        return ConversationResponse.builder()
                .id(session.getId())
                .question(session.getQuestion())
                .answer(session.getAnswer())
                .createdDate(session.getCreatedDate())
                .build();
    }

    public void deleteConversation(
            Long id) {

        repository.deleteById(id);

        log.info(
                "Conversation Deleted : {}",
                id);
    }
}
