package com.varma.api.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varma.domain.service.ChatService;
import com.varma.model.ChatRequest;
import com.varma.model.ChatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ChatResourceImplTest {
    private MockMvc mockMvc;
    @Mock
    private ChatService chatService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ChatResourceImpl(chatService))
                .build();
    }

    @Test
    void testQuery() throws Exception {

        ChatRequest request = new ChatRequest();
        request.setPrompt("Hello");
        ChatResponse response = ChatResponse.builder()
                .response("Hi")
                .build();

        when(chatService.query(any())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/chat/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value("Hi"));
        verify(chatService).query(any());
    }
}
