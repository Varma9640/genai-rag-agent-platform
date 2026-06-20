package com.varma.domain.service;

import com.varma.document.DocumentProcessor;
import com.varma.domain.entity.DocumentMetadata;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.model.DocumentUploadRequest;
import com.varma.model.DocumentUploadResponse;
import com.varma.vector.ChromaDbService;
import com.varma.vector.EmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentProcessor documentProcessor;
    private final ChunkingService chunkingService;
    private final EmbeddingService embeddingService;
    private final ChromaDbService chromaDbService;
    private final MetricsService metricsService;
    private final DocumentMetadataRepository documentMetadataRepository;

    public DocumentUploadResponse upload(DocumentUploadRequest request) {

        String documentName = request.getFile().getOriginalFilename();
        log.info("Processing Document : {}", documentName);
        String extractedText = documentProcessor.processDocument(request);
        List<String> chunks = chunkingService.chunk(extractedText);
        for (int index = 0; index < chunks.size(); index++) {
            String chunk = chunks.get(index);
            float[] embedding = embeddingService.generateEmbedding(chunk);
            chromaDbService.storeChunk(documentName,
                    index + 1,
                    chunk, embedding);
        }

        documentMetadataRepository.save(DocumentMetadata.builder()
                        .documentName(documentName)
                        .totalChunks(chunks.size())
                        .uploadedDate(LocalDateTime.now())
                        .build());

        metricsService.incrementDocumentUploadCount();

        log.info("Document Uploaded Successfully : {}", documentName);
        return DocumentUploadResponse.builder()
                .documentName(documentName)
                .status("SUCCESS")
                .message("PDF uploaded successfully")
                .build();
    }
    @Transactional
    public void delete(Long id) {

        log.info("Deleting Document Id : {}", id);
        DocumentMetadata metadata = documentMetadataRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Document Not Found : " + id));

        documentMetadataRepository.delete(metadata);
        log.info("Document Deleted : {}",
                metadata.getDocumentName());
    }
}
