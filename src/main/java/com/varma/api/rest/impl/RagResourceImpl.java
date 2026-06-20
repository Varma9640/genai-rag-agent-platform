package com.varma.api.rest.impl;

import com.varma.api.rest.RagResource;
import com.varma.domain.service.RagService;
import com.varma.model.RagQueryRequest;
import com.varma.model.RagQueryResponse;
import com.varma.model.RetrievalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RagResourceImpl implements RagResource {
    private final RagService ragService;

    @Override
    public ResponseEntity<RagQueryResponse> ask(RagQueryRequest request) {
        return ResponseEntity.ok(ragService.askQuestion(request));
    }

    @Override
    public ResponseEntity<List<RetrievalResponse>> debug(RagQueryRequest request) {
        return ResponseEntity.ok(ragService.debugRetrieval(request));
    }
}
