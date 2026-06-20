package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RagQueryRequest;
import com.varma.model.RagQueryResponse;
import com.varma.model.RetrievalResponse;
import com.varma.model.RouteType;
import com.varma.model.SourceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RagService {
    private final AgentRoutingService agentRoutingService;
    private final RagQueryService ragQueryService;
    private final QuestionClassifierService questionClassifierService;

    public RagQueryResponse askQuestion(RagQueryRequest request) {
        RouteType route = questionClassifierService.classify(request.getQuestion());
        String answer = agentRoutingService.ask(request);
        List<DocumentChunk> chunks = ragQueryService.retrieveChunks(request.getQuestion());
        List<SourceResponse> sources =
                chunks.stream()
                        .map(chunk ->
                                SourceResponse.builder()
                                        .documentName(
                                                chunk.getDocumentName())
                                        .chunkNumber(
                                                chunk.getChunkNumber())
                                        .build())
                        .toList();

        return RagQueryResponse.builder()
                .question(request.getQuestion())
                .answer(answer)
                .route(route.name())
                .sources(sources)
                .build();
    }
    public List<RetrievalResponse> debugRetrieval(RagQueryRequest request) {
        return ragQueryService.debugRetrieval(request.getQuestion());
    }
}
