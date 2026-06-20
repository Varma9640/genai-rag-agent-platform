package com.varma.domain.service;

import com.varma.agent.AgentOrchestrator;
import com.varma.model.AgentQueryRequest;
import com.varma.model.AgentQueryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {
    @InjectMocks
    private AgentService service;

    @Mock
    private AgentOrchestrator agentOrchestrator;

    @Test
    void testQuery() {

        AgentQueryRequest request = new AgentQueryRequest();
        request.setQuestion("What is Spring Boot?");

        when(agentOrchestrator.execute("What is Spring Boot?"))
                .thenReturn("Spring Boot Answer");
        AgentQueryResponse response = service.query(request);
        assertEquals("What is Spring Boot?", response.getQuestion());
        assertEquals("Spring Boot Answer", response.getAnswer());
    }
}
