package com.varma.api.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varma.domain.service.StreamingService;
import com.varma.model.RagQueryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StreamingResourceImplTest {
    private MockMvc mockMvc;

    @Mock
    private StreamingService streamingService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new StreamingResourceImpl(streamingService))
                .build();
    }

    @Test
    void testStream() throws Exception {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("SESSION1");
        when(streamingService.streamResponse(any())).thenReturn(
                new SseEmitter());

        mockMvc.perform(post("/api/v1/stream/stream")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(request)))
                .andExpect(status().isOk());
        verify(streamingService).streamResponse(any());
    }
}
