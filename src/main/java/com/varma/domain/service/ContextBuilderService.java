package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContextBuilderService {
    public String buildContext(List<DocumentChunk> chunks) {

        StringBuilder contextBuilder = new StringBuilder();
        for (DocumentChunk chunk : chunks) {contextBuilder
                    .append(chunk.getContent())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
        String context = contextBuilder.toString();
        log.info("Context Length : {}", context.length());
        return context;
    }
}
