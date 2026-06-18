package com.varma.domain.service;

import com.varma.domain.repository.ChatSessionRepository;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.domain.repository.PromptAuditRepository;
import com.varma.domain.repository.QueryHistoryRepository;
import com.varma.model.MetricsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final QueryHistoryRepository queryHistoryRepository;
    private final DocumentMetadataRepository documentMetadataRepository;
    private final PromptAuditRepository promptAuditRepository;
    private final ChatSessionRepository chatSessionRepository;

    public MetricsResponse getMetrics() {
        return MetricsResponse.builder()
                .totalQuestions(queryHistoryRepository.count())
                .totalDocuments(documentMetadataRepository.count())
                .totalPromptAudits(promptAuditRepository.count())
                .totalChatSessions(chatSessionRepository.count())
                .build();
    }
}
