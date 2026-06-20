package com.varma.domain.service;

import com.varma.domain.entity.DocumentMetadata;
import com.varma.domain.repository.DocumentMetadataRepository;
import com.varma.model.DocumentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DocumentManagementServiceTest {
    @InjectMocks
    private DocumentManagementService service;

    @Mock
    private DocumentMetadataRepository repository;

    @Test
    void testGetDocuments() {

        DocumentMetadata metadata = DocumentMetadata.builder()
                .id(1L)
                .documentName("spring.pdf")
                .totalChunks(5)
                .build();

        when(repository.findAll()).thenReturn(List.of(metadata));
        List<DocumentResponse> response = service.getDocuments();
        assertEquals(1, response.size());
        assertEquals("spring.pdf", response.get(0).getDocumentName());
    }

    @Test
    void testGetDocument() {

        DocumentMetadata metadata = DocumentMetadata.builder()
                .id(1L)
                .documentName("spring.pdf")
                .totalChunks(5)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(metadata));
        DocumentResponse response = service.getDocument(1L);
        assertEquals("spring.pdf", response.getDocumentName());
    }

    @Test
    void testDeleteDocument() {

        service.deleteDocument(1L);
        verify(repository).deleteById(1L);
    }
}
