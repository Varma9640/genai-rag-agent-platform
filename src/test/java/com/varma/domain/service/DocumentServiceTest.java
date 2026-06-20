package com.varma.domain.service;

import com.varma.document.DocumentProcessor;
import com.varma.domain.entity.DocumentMetadata;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.model.DocumentUploadRequest;
import com.varma.model.DocumentUploadResponse;
import com.varma.vector.ChromaDbService;
import com.varma.vector.EmbeddingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {
    @InjectMocks
    private DocumentService service;

    @Mock
    private DocumentProcessor documentProcessor;

    @Mock
    private ChunkingService chunkingService;

    @Mock
    private EmbeddingService embeddingService;

    @Mock
    private ChromaDbService chromaDbService;

    @Mock
    private MetricsService metricsService;

    @Mock
    private DocumentMetadataRepository documentMetadataRepository;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private DocumentUploadRequest request;

    @Test
    void testUpload() {

        when(request.getFile()).thenReturn(multipartFile);
        when(multipartFile.getOriginalFilename()).thenReturn("spring.pdf");
        when(documentProcessor.processDocument(request)).thenReturn("Spring Boot Sample Text");
        List<String> chunks = List.of("chunk1", "chunk2");
        when(chunkingService.chunk(anyString())).thenReturn(chunks);
        when(embeddingService.generateEmbedding("chunk1")).thenReturn(new float[]{1f});
        when(embeddingService.generateEmbedding("chunk2")).thenReturn(new float[]{2f});

        DocumentUploadResponse response = service.upload(request);
        assertNotNull(response);
        assertEquals("spring.pdf", response.getDocumentName());
        assertEquals("SUCCESS", response.getStatus());
        verify(documentProcessor).processDocument(request);
        verify(chunkingService).chunk(anyString());
        verify(chromaDbService, times(2))
                .storeChunk(anyString(), anyInt(), anyString(), any());
        verify(documentMetadataRepository).save(any(DocumentMetadata.class));
        verify(metricsService).incrementDocumentUploadCount();
    }

    @Test
    void testDelete() {

        DocumentMetadata metadata = DocumentMetadata.builder()
                .id(1L)
                .documentName("spring.pdf")
                .build();

        when(documentMetadataRepository.findById(1L)).thenReturn(Optional.of(metadata));
        service.delete(1L);
        verify(documentMetadataRepository).delete(metadata);
    }

    @Test
    void testDeleteDocumentNotFound() {

        when(documentMetadataRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> service.delete(1L));
        assertEquals("Document Not Found : 1", exception.getMessage());
    }
}
