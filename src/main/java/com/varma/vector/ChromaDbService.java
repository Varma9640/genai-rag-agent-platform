package com.varma.vector;

import com.varma.model.DocumentChunk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ChromaDbService {
    private final List<DocumentChunk> store = new ArrayList<>();

    public void storeChunk(String documentName, Integer chunkNumber, String chunk, float[] embedding) {
        DocumentChunk documentChunk = DocumentChunk.builder()
                .id(UUID.randomUUID().toString())
                .documentName(documentName)
                .chunkNumber(chunkNumber)
                .content(chunk)
                .embedding(embedding)
                .build();

        store.add(documentChunk);
        log.info("Stored {} Chunk-{}", documentName, chunkNumber);
    }

    public List<DocumentChunk> getAllChunks() {
        return store;
    }
}
