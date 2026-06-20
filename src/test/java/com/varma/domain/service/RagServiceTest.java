package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RagQueryRequest;
import com.varma.model.RagQueryResponse;
import com.varma.model.RetrievalResponse;
import com.varma.model.RouteType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RagServiceTest {
    @InjectMocks
    private RagService service;

    @Mock
    private AgentRoutingService agentRoutingService;

    @Mock
    private RagQueryService ragQueryService;

    @Mock
    private QuestionClassifierService questionClassifierService;

    @Test
    void testAskQuestion() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("Spring Boot");
        request.setSessionId("SESSION1");

        DocumentChunk chunk = DocumentChunk.builder()
                .documentName("spring.pdf")
                .chunkNumber(1)
                .build();

        when(questionClassifierService.classify(anyString()))
                .thenReturn(RouteType.RAG);
        when(agentRoutingService.ask(any())).thenReturn("Answer");
        when(ragQueryService.retrieveChunks(anyString())).thenReturn(List.of(chunk));
        RagQueryResponse response = service.askQuestion(request);
        assertEquals("Answer", response.getAnswer());
        assertEquals("RAG", response.getRoute());
        assertEquals(1, response.getSources().size());
    }

    @Test
    void testDebugRetrieval() {

        when(ragQueryService.debugRetrieval(anyString()))
                .thenReturn(Collections.emptyList());
        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("Spring");
        List<RetrievalResponse> response = service.debugRetrieval(request);
        assertNotNull(response);
    }
}
