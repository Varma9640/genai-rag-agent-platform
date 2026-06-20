package com.varma.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LlmFactoryServiceTest {

    @InjectMocks
    private LlmFactoryService service;

    @Mock
    private OllamaLlmClientService ollama;

    @Mock
    private OpenAiLlmClientService openAi;

    @Test
    void testGetClientOpenAi() {

        ReflectionTestUtils.setField(service, "provider", "openai");

        LlmClientService client = service.getClient();
        assertEquals(openAi, client);
    }

    @Test
    void testGetClientOllama() {

        ReflectionTestUtils.setField(service, "provider", "ollama");
        LlmClientService client = service.getClient();
        assertEquals(ollama, client);
    }
}