package com.varma.domain.service;

import com.varma.domain.entity.DocumentMetadata;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.model.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentManagementService {
    private final DocumentMetadataRepository documentMetadataRepository;

    public List<DocumentResponse> getDocuments() {

        return documentMetadataRepository.findAll()
                .stream()
                .map(document ->
                        DocumentResponse.builder()
                                .id(document.getId())
                                .documentName(
                                        document.getDocumentName())
                                .totalChunks(
                                        document.getTotalChunks())
                                .uploadedDate(
                                        document.getUploadedDate())
                                .build())
                .toList();
    }

    public DocumentResponse getDocument(Long id) {

        DocumentMetadata document = documentMetadataRepository.findById(id)
                        .orElseThrow();

        return DocumentResponse.builder()
                .id(document.getId())
                .documentName(document.getDocumentName())
                .totalChunks(document.getTotalChunks())
                .uploadedDate(document.getUploadedDate())
                .build();
    }

    public void deleteDocument(Long id) {

        documentMetadataRepository.deleteById(id);
    }
}
