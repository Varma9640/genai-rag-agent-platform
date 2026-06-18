package com.varma.rag;
/*
import com.varma.domain.service.ContextBuilderService;
import com.varma.domain.service.LlmClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RagEngine {
    private final RetrievalService retrievalService;
    private final ContextBuilderService contextBuilderService;
    private final PromptTemplateService promptTemplateService;
    private final LlmClientService llmClientService;

    public String generateAnswer(String question) {

        String retrievedContext = retrievalService.retrieveContext(question);
        String context = contextBuilderService.buildContext(retrievedContext);
        String prompt = promptTemplateService.buildPrompt(question, context);

        return llmClientService.generateResponse(prompt);
    }
}*/
