package com.varma.api.rest;

import com.varma.api.swagger.DocumentManagementSwagger;
import com.varma.model.DocumentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Document Management Resource")
@DocumentManagementSwagger
@RequestMapping(value = "/api/v1/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public interface DocumentManagementResource {
    @Operation(summary = "Get Documents", description = "Retrieve all uploaded documents")
    @GetMapping
    ResponseEntity<List<DocumentResponse>> getDocuments();

    @Operation(summary = "Get Document Details", description = "Retrieve document details by id")
    @GetMapping("/{id}")
    ResponseEntity<DocumentResponse> getDocument(@PathVariable Long id);

    @Operation(summary = "Delete Document", description = "Delete uploaded document")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDocument(@PathVariable Long id);
}
