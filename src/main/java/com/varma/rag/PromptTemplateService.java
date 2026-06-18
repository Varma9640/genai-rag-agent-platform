package com.varma.rag;
/*
import org.springframework.stereotype.Service;

@Service
public class PromptTemplateService {
    private static final String SYSTEM_PROMPT = """
            You are an enterprise AI knowledge assistant.

            Rules:
            1. Answer only from provided context.
            2. Do not hallucinate.
            3. If answer is unavailable in context, respond with:
               'Information not available in provided documents.'
            4. Keep answers professional and concise.
            """;

    public String buildPrompt(String question, String context) {

        return """
                %s

                Context:
                %s

                Question:
                %s

                Answer:
                """
                .formatted(SYSTEM_PROMPT, context, question);
    }
}*/
