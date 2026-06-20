package com.varma.api.rest.impl;

import com.varma.domain.service.DocumentManagementService;
import com.varma.model.DocumentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DocumentManagementResourceImplTest {
    private MockMvc mockMvc;

    @Mock
    private DocumentManagementService service;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DocumentManagementResourceImpl(service))
                .build();
    }

    @Test
    void testGetDocuments() throws Exception {

        DocumentResponse response = DocumentResponse.builder()
                .id(1L)
                .documentName("spring.pdf")
                .totalChunks(5)
                .build();

        when(service.getDocuments()).thenReturn(List.of(response));
        mockMvc.perform(get("/api/v1/documents"))
                .andExpect(status().isOk());
        verify(service).getDocuments();
    }

    @Test
    void testGetDocument() throws Exception {

        DocumentResponse response = DocumentResponse.builder()
                .id(1L)
                .documentName("spring.pdf")
                .totalChunks(5)
                .build();

        when(service.getDocument(1L)).thenReturn(response);
        mockMvc.perform(get("/api/v1/documents/1"))
                .andExpect(status().isOk());
        verify(service).getDocument(1L);
    }

    @Test
    void testDeleteDocument() throws Exception {

        mockMvc.perform(delete("/api/v1/documents/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteDocument(1L);
    }
}
