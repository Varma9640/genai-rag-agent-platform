package com.varma.api.rest.impl;

import com.varma.domain.service.ConversationManagementService;
import com.varma.model.ConversationResponse;
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
public class ConversationResourceImplTest {
    private MockMvc mockMvc;
    @Mock
    private ConversationManagementService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                                new ConversationResourceImpl(service)).build();
    }

    @Test
    void testGetConversations() throws Exception {
        ConversationResponse response = ConversationResponse.builder()
                        .id(1L)
                        .question("Java")
                        .answer("Java Answer")
                        .build();

        when(service.getConversations()).thenReturn(List.of(response));
        mockMvc.perform(get("/api/v1/conversations"))
                .andExpect(status().isOk());
        verify(service).getConversations();
    }

    @Test
    void testGetConversation() throws Exception {

        ConversationResponse response = ConversationResponse.builder()
                        .id(1L)
                        .question("Java")
                        .answer("Java Answer")
                        .build();
        when(service.getConversation(1L)).thenReturn(response);
        mockMvc.perform(get("/api/v1/conversations/1"))
                .andExpect(status().isOk());
        verify(service).getConversation(1L);
    }

    @Test
    void testDeleteConversation() throws Exception {

        mockMvc.perform(delete("/api/v1/conversations/1"))
                .andExpect(status().isNoContent());
        verify(service).deleteConversation(1L);
    }
}
