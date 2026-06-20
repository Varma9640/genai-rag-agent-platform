package com.varma.agent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerGenerationToolTest {
    @Test
    void testGenerateAnswer() {
        AnswerGenerationTool tool = new AnswerGenerationTool();
        String result = tool.generateAnswer("Context");
        assertEquals("Generated Agent Answer : Context", result);
    }
}
