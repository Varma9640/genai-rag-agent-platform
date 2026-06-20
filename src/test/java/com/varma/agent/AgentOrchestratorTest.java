package com.varma.agent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgentOrchestratorTest {

    @InjectMocks
    private AgentOrchestrator agentOrchestrator;

    @Mock
    private DocumentSearchTool documentSearchTool;

    @Mock
    private ContextRetrievalTool contextRetrievalTool;

    @Mock
    private AnswerGenerationTool answerGenerationTool;

    @Test
    void testExecute() {

        when(documentSearchTool.search("Spring")).thenReturn("Document");
        when(contextRetrievalTool.retrieve("Document")).thenReturn("Context");
        when(answerGenerationTool.generateAnswer("Context")).thenReturn("Final Answer");
        String result = agentOrchestrator.execute("Spring");
        assertEquals("Final Answer", result);
    }
}