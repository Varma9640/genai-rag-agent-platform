package com.varma.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChunkingService {
    public List<String> chunk(String text) {

        List<String> chunks = new ArrayList<>();
        int chunkSize = 500;
        for (int i = 0; i < text.length(); i += chunkSize) {
            chunks.add(text.substring(i, Math.min(i + chunkSize, text.length())));
        }
        log.info("Total Chunks Created : {}", chunks.size());
        return chunks;
    }
}
