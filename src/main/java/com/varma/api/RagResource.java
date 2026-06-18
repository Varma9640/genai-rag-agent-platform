package com.varma.api;

import com.varma.api.swagger.RagQuerySwagger;
import com.varma.model.RagQueryRequest;
import com.varma.model.RagQueryResponse;
import com.varma.model.RetrievalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@Tag(name = "RAG Resource")
@RagQuerySwagger
@RequestMapping(value = "/api/v1/rag", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RagResource {
    @Operation(summary = "Query RAG Engine", description = "Retrieve answer using RAG")
    @PostMapping("/ask")
    ResponseEntity<RagQueryResponse> ask(@Valid @RequestBody RagQueryRequest request);

    @Operation(summary = "Debug Retrieval", description = "Retrieve matching chunks")
    @PostMapping("/debug")
    ResponseEntity<List<RetrievalResponse>> debug(@Valid @RequestBody RagQueryRequest request);
}
