package com.varma.api.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varma.domain.service.RagService;
import com.varma.model.RagQueryRequest;
import com.varma.model.RagQueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RagResourceImplTest {
    private MockMvc mockMvc;

    @Mock
    private RagService ragService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new RagResourceImpl(ragService))
                .build();
    }

    @Test
    void testAsk() throws Exception {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("S1");

        RagQueryResponse response = RagQueryResponse.builder()
                .question("What is Spring Boot?")
                .answer("Spring Boot Answer")
                .route("RAG")
                .sources(List.of())
                .build();

        when(ragService.askQuestion(any())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/rag/ask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answer")
                        .value("Spring Boot Answer"));
        verify(ragService).askQuestion(any());
    }

    @Test
    void testDebug() throws Exception {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("Java");
        request.setSessionId("S1");

        when(ragService.debugRetrieval(any())).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/rag/debug")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(ragService).debugRetrieval(any());
    }
}
