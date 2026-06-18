package com.varma.agent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgentOrchestrator {
    private final DocumentSearchTool documentSearchTool;
    private final ContextRetrievalTool contextRetrievalTool;
    private final AnswerGenerationTool answerGenerationTool;

    public String execute(String question) {

        String documentResult = documentSearchTool.search(question);

        String context = contextRetrievalTool.retrieve(documentResult);

        return answerGenerationTool.generateAnswer(context);
    }
}
