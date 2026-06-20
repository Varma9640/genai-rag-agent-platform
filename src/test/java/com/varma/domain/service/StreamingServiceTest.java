package com.varma.domain.service;

import com.varma.model.RagQueryRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class StreamingServiceTest {
    @InjectMocks
    private StreamingService service;

    @Mock
    private AgentRoutingService agentRoutingService;

    @Test
    void testStreamResponse() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("SESSION1");
        lenient().when(agentRoutingService.ask(any())).thenReturn("Spring Boot is framework");

        SseEmitter emitter = service.streamResponse(request);
        assertNotNull(emitter);
    }

    @Test
    void testStreamResponseException() {

        RagQueryRequest request = new RagQueryRequest();
        request.setQuestion("What is Spring Boot?");
        request.setSessionId("SESSION1");
        lenient().when(agentRoutingService.ask(any()))
                .thenThrow(new RuntimeException("Streaming Error"));
        SseEmitter emitter = service.streamResponse(request);
        assertNotNull(emitter);
    }
}
