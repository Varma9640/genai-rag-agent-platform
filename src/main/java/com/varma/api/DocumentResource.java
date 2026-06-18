package com.varma.api;

import com.varma.api.swagger.DocumentUploadSwagger;
import com.varma.model.DocumentUploadRequest;
import com.varma.model.DocumentUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "Document Resource")
@DocumentUploadSwagger
@RequestMapping(value = "/api/v1/documents", produces = MediaType.APPLICATION_JSON_VALUE)
public interface DocumentResource {
    @Operation(summary = "Upload PDF Document", description = "Upload PDF document into RAG platform")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    ResponseEntity<DocumentUploadResponse> upload(@ModelAttribute DocumentUploadRequest requestF);

    @Operation(summary = "Delete Document", description = "Delete document by id")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(
            @PathVariable Long id);
}
