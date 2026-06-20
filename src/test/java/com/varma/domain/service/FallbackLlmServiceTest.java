package com.varma.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FallbackLlmServiceTest {
    @InjectMocks
    private FallbackLlmService service;

    @Mock
    private LlmFactoryService llmFactoryService;

    @Mock
    private OllamaLlmClientService ollamaLlmClientService;

    @Mock
    private LlmClientService llmClientService;

    @Test
    void testGenerateResponsePrimarySuccess() {

        when(llmFactoryService.getClient()).thenReturn(llmClientService);
        when(llmClientService.generateResponse(anyString())).thenReturn("OpenAI Response");

        String response = service.generateResponse("Explain Spring Boot");

        assertEquals("OpenAI Response", response);
        verify(ollamaLlmClientService, never()).generateResponse(anyString());
    }

    @Test
    void testGenerateResponseFallbackToOllama() {

        when(llmFactoryService.getClient()).thenReturn(llmClientService);
        when(llmClientService.generateResponse(anyString()))
                .thenThrow(new RuntimeException("LLM Error"));
        when(ollamaLlmClientService.generateResponse(anyString()))
                .thenReturn("Ollama Response");

        String response = service.generateResponse("Explain Spring Boot");
        assertEquals("Ollama Response", response);
        verify(ollamaLlmClientService).generateResponse(anyString());
    }
}
