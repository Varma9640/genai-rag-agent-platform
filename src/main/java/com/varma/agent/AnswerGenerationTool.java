package com.varma.agent;

import org.springframework.stereotype.Component;

@Component
public class AnswerGenerationTool {
    public String generateAnswer(String context) {

        return "Generated Agent Answer : " + context;
    }
}
