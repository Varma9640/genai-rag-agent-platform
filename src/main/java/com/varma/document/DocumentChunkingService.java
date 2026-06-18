package com.varma.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentChunkingService {
    public List<String> chunkDocument(String documentContent) {
        log.info("Chunking document");
        return List.of(documentContent);
    }
}
