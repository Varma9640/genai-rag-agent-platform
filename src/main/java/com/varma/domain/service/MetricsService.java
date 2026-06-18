package com.varma.domain.service;

import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricsService {
    private final Counter ragQueriesCounter;
    private final Counter documentUploadsCounter;

    public MetricsService(MeterRegistry meterRegistry) {

        this.ragQueriesCounter = Counter.builder("rag_queries_total")
                        .description("Total RAG Queries")
                        .register(meterRegistry);

        this.documentUploadsCounter = Counter.builder("documents_uploaded_total")
                        .description("Total Uploaded Documents")
                        .register(meterRegistry);
    }

    public void incrementQueryCount() {
        ragQueriesCounter.increment();
    }

    public void incrementDocumentUploadCount() {
        documentUploadsCounter.increment();
    }
}
