package com.varma.api.rest.impl;

import com.varma.api.DocumentManagementResource;
import com.varma.domain.service.DocumentManagementService;
import com.varma.model.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentManagementResourceImpl implements DocumentManagementResource {
    private final DocumentManagementService service;

    @Override
    public ResponseEntity<List<DocumentResponse>>
    getDocuments() {
        return ResponseEntity.ok(service.getDocuments());
    }

    @Override
    public ResponseEntity<DocumentResponse>
    getDocument(Long id) {
        return ResponseEntity.ok(service.getDocument(id));
    }

    @Override
    public ResponseEntity<Void>
    deleteDocument(Long id) {
        service.deleteDocument(id);
        return ResponseEntity.noContent()
                .build();
    }
}
