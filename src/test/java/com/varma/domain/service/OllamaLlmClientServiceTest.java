package com.varma.domain.service;

import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OllamaLlmClientServiceTest {

    @Mock
    private OllamaChatModel ollamaChatModel;

    @InjectMocks
    private OllamaLlmClientService service;

    @Test
    void testGenerateResponse() {

        when(ollamaChatModel.generate("Hello")).thenReturn("Ollama Response");
        String result = service.generateResponse("Hello");
        assertEquals("Ollama Response", result);
    }
}