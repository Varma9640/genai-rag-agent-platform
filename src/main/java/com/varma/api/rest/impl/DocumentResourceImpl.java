package com.varma.api.rest.impl;

import com.varma.api.DocumentResource;
import com.varma.domain.service.DocumentService;
import com.varma.model.DocumentUploadRequest;
import com.varma.model.DocumentUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentResourceImpl implements DocumentResource {
    private final DocumentService documentService;

    @Override
    public ResponseEntity<DocumentUploadResponse> upload(DocumentUploadRequest request) {
        return ResponseEntity.ok(documentService.upload(request));
    }
    @Override
    public ResponseEntity<Void> delete(Long id) {
        documentService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
