package com.varma.domain.service;

import com.varma.model.ChatRequest;
import com.varma.model.ChatResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {
    @InjectMocks
    private ChatService service;

    @Mock
    private OllamaLlmClientService ollamaLlmClientService;

    @Test
    void testQuery() {

        ChatRequest request = new ChatRequest();
        request.setPrompt("Hello");
        when(ollamaLlmClientService.generateResponse("Hello"))
                .thenReturn("Hi");

        ChatResponse response = service.query(request);
        assertEquals("Hi", response.getResponse());
    }
}
