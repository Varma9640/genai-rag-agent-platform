package com.varma.domain.service;

import com.varma.domain.repository.ChatSessionRepository;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.domain.repository.PromptAuditRepository;
import com.varma.domain.repository.QueryHistoryRepository;
import com.varma.model.MetricsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {
    @InjectMocks
    private DashboardService service;

    @Mock
    private QueryHistoryRepository queryHistoryRepository;

    @Mock
    private DocumentMetadataRepository documentMetadataRepository;

    @Mock
    private PromptAuditRepository promptAuditRepository;

    @Mock
    private ChatSessionRepository chatSessionRepository;

    @Test
    void testGetMetrics() {

        when(queryHistoryRepository.count()).thenReturn(10L);
        when(documentMetadataRepository.count()).thenReturn(5L);
        when(promptAuditRepository.count()).thenReturn(3L);
        when(chatSessionRepository.count()).thenReturn(7L);
        MetricsResponse response = service.getMetrics();
        assertNotNull(response);
        assertEquals(10L, response.getTotalQuestions());
        assertEquals(5L, response.getTotalDocuments());
        assertEquals(3L, response.getTotalPromptAudits());
        assertEquals(7L, response.getTotalChatSessions());
    }
}
