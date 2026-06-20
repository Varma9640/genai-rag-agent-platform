package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RagQueryRequest;
import com.varma.model.RetrievalResponse;
import com.varma.model.RetrievalResult;
import com.varma.vector.EmbeddingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RagQueryServiceTest {
    @InjectMocks
    private RagQueryService service;

    @Mock
    private EmbeddingService embeddingService;

    @Mock
    private SimilaritySearchService similaritySearchService;

    @Mock
    private ReRankingService reRankingService;

    @Mock
    private ContextBuilderService contextBuilderService;

    @Mock
    private QueryHistoryService queryHistoryService;

    @Mock
    private PromptAuditService promptAuditService;

    @Mock
    private ChatMemoryService chatMemoryService;

    @Mock
    private MetricsService metricsService;

    @Mock
    private FallbackLlmService fallbackLlmService;

    @Test
    void testAskSuccess() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("SESSION1");
        float[] embedding = new float[]{1f, 2f};

        DocumentChunk chunk = DocumentChunk.builder()
                .documentName("spring.pdf")
                .chunkNumber(1)
                .content("Spring Boot Content")
                .embedding(new float[]{1f})
                .build();

        RetrievalResult retrievalResult = RetrievalResult.builder()
                .chunk(chunk)
                .vectorScore(0.9)
                .keywordScore(0.8)
                .finalScore(0.85)
                .build();

        when(embeddingService.generateEmbedding(anyString())).thenReturn(embedding);
        when(similaritySearchService.findRelevantChunks(anyString(), any())).thenReturn(List.of(retrievalResult));
        when(reRankingService.reRank(anyList())).thenReturn(List.of(chunk));
        when(contextBuilderService.buildContext(anyList())).thenReturn("Context");
        when(chatMemoryService.getRecentConversation(anyString())).thenReturn("Previous Conversation");
        when(fallbackLlmService.generateResponse(anyString())).thenReturn("Spring Boot Answer");

        String response = service.ask(request);
        assertEquals("Spring Boot Answer", response);
        verify(metricsService).incrementQueryCount();
        verify(promptAuditService).save(anyString(), anyString(), anyString(), anyString());
        verify(queryHistoryService).save(anyString(), anyString());
        verify(chatMemoryService).save(anyString(), anyString(), anyString());
    }

    @Test
    void testAskNoChunksFound() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("Unknown Question");
        request.setSessionId("SESSION1");
        when(embeddingService.generateEmbedding(anyString())).thenReturn(new float[]{1f});
        when(similaritySearchService.findRelevantChunks(anyString(), any())).thenReturn(Collections.emptyList());
        when(reRankingService.reRank(anyList())).thenReturn(Collections.emptyList());
        String response = service.ask(request);
        assertTrue(response.contains("I could not find relevant information"));
    }

    @Test
    void testRetrieveChunks() {

        DocumentChunk chunk = DocumentChunk.builder()
                .documentName("spring.pdf")
                .chunkNumber(1)
                .content("content")
                .build();

        RetrievalResult retrievalResult = RetrievalResult.builder()
                .chunk(chunk)
                .build();

        when(embeddingService.generateEmbedding(anyString()))
                .thenReturn(new float[]{1f});
        when(similaritySearchService.findRelevantChunks(anyString(), any()))
                .thenReturn(List.of(retrievalResult));
        when(reRankingService.reRank(anyList())).thenReturn(List.of(chunk));
        List<DocumentChunk> result = service.retrieveChunks("Spring");
        assertEquals(1, result.size());
    }

    @Test
    void testDebugRetrieval() {

        DocumentChunk chunk = DocumentChunk.builder()
                .documentName("spring.pdf")
                .chunkNumber(1)
                .content("content")
                .build();

        RetrievalResult retrievalResult = RetrievalResult.builder()
                .chunk(chunk)
                .build();

        when(embeddingService.generateEmbedding(anyString()))
                .thenReturn(new float[]{1f});
        when(similaritySearchService.findRelevantChunks(anyString(), any()))
                .thenReturn(List.of(retrievalResult));
        when(reRankingService.reRank(anyList()))
                .thenReturn(List.of(chunk));
        List<RetrievalResponse> response = service.debugRetrieval("Spring");
        assertEquals(1, response.size());
    }
}
