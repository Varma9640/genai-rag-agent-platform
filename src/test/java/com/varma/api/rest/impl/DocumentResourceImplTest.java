package com.varma.api.rest.impl;

import com.varma.domain.service.DocumentService;
import com.varma.model.DocumentUploadRequest;
import com.varma.model.DocumentUploadResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DocumentResourceImplTest {
    private MockMvc mockMvc;
    @Mock
    private DocumentService documentService;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DocumentResourceImpl(documentService))
                .build();
    }

    @Test
    void testUpload() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "spring.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "sample pdf".getBytes());

        DocumentUploadResponse response = DocumentUploadResponse.builder()
                .documentName("spring.pdf")
                .status("SUCCESS")
                .message("PDF uploaded successfully")
                .build();

        when(documentService.upload(any())).thenReturn(response);

        mockMvc.perform(multipart("/api/v1/documents/upload")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.documentName")
                        .value("spring.pdf"))
                .andExpect(jsonPath("$.status")
                        .value("SUCCESS"));

        verify(documentService).upload(any(DocumentUploadRequest.class));
    }

    @Test
    void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/documents/delete/1"))
                .andExpect(status().isNoContent());
        verify(documentService).delete(1L);
    }
}
