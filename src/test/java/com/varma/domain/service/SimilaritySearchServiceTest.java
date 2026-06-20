package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RetrievalResult;
import com.varma.vector.ChromaDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SimilaritySearchServiceTest {
    @InjectMocks
    private SimilaritySearchService service;

    @Mock
    private ChromaDbService chromaDbService;

    @Test
    void testFindRelevantChunks() {

        DocumentChunk chunk = DocumentChunk.builder()
                .documentName("spring.pdf")
                .chunkNumber(1)
                .content("Spring Boot Framework")
                .embedding(new float[]{1f, 2f})
                .build();

        when(chromaDbService.getAllChunks()).thenReturn(List.of(chunk));
        List<RetrievalResult> results = service.findRelevantChunks("Spring Boot", new float[]{1f, 2f});
        assertNotNull(results);
    }

    @Test
    void testFindRelevantChunksEmpty() {

        when(chromaDbService.getAllChunks()).thenReturn(Collections.emptyList());
        List<RetrievalResult> results = service.findRelevantChunks("Spring Boot", new float[]{1f});
        assertTrue(results.isEmpty());
    }
}
