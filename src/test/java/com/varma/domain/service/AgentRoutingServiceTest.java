package com.varma.domain.service;

import com.varma.model.RagQueryRequest;
import com.varma.model.RouteType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AgentRoutingServiceTest {
    @InjectMocks
    private AgentRoutingService service;

    @Mock
    private QuestionClassifierService questionClassifierService;

    @Mock
    private RagQueryService ragQueryService;

    @Mock
    private GeneralChatService generalChatService;

    @Test
    void testAskRagRoute() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("SESSION1");

        when(questionClassifierService.classify(anyString()))
                .thenReturn(RouteType.RAG);
        when(ragQueryService.ask(any()))
                .thenReturn("Spring Boot Answer");
        String response = service.ask(request);
        assertEquals("Spring Boot Answer", response);
        verify(ragQueryService).ask(any());
        verify(generalChatService, never()).ask(anyString());
    }

    @Test
    void testAskGeneralRoute() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("How are you?");
        request.setSessionId("SESSION1");

        when(questionClassifierService.classify(anyString()))
                .thenReturn(RouteType.GENERAL);
        when(generalChatService.ask(anyString()))
                .thenReturn("I am fine");
        String response = service.ask(request);
        assertEquals("I am fine", response);
        verify(generalChatService).ask(anyString());
        verify(ragQueryService, never()).ask(any());
    }
}
