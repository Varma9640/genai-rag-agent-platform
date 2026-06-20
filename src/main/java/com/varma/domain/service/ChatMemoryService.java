package com.varma.domain.service;

import com.varma.domain.entity.ChatSession;
import com.varma.domain.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {
    private final ChatSessionRepository chatSessionRepository;

    public void save(String sessionId, String question, String answer) {

        chatSessionRepository.save(
                ChatSession.builder()
                        .sessionId(sessionId)
                        .question(question)
                        .answer(answer)
                        .createdDate(LocalDateTime.now())
                        .build());
    }

    public String getRecentConversation(String sessionId) {

        return chatSessionRepository
                .findBySessionIdOrderByCreatedDateAsc(sessionId)
                .stream()
                .map(chat ->
                        """
                                Question:
                                %s
                                
                                Answer:
                                %s
                                """
                                .formatted(chat.getQuestion(), chat.getAnswer()))
                .collect(Collectors.joining("\n"));
    }
}
