package com.varma.vector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VectorSearchService {
    public String search(String question) {
        log.info("Searching vector database");
        return "Retrieved Context";
    }
}
