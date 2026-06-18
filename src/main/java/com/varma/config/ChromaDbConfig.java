package com.varma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChromaDbConfig {
    @Value("${chromadb.url}")
    private String chromaDbUrl;

    public String getChromaDbUrl() {
        return chromaDbUrl;
    }
}
