package com.varma.domain.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ChunkingServiceTest {
    private final ChunkingService service =
            new ChunkingService();

    @Test
    void testChunkSingleChunk() {

        String text = "Spring Boot";
        List<String> chunks = service.chunk(text);
        assertEquals(1, chunks.size());
    }

    @Test
    void testChunkMultipleChunks() {

        String text = "A".repeat(1200);
        List<String> chunks = service.chunk(text);
        assertEquals(3, chunks.size());
    }

    @Test
    void testChunkEmptyText() {

        List<String> chunks = service.chunk("");
        assertTrue(chunks.isEmpty());
    }
}
