package com.varma.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAiLlmClientService implements LlmClientService {

    @Override
    public String generateResponse(String prompt) {
        log.info("Invoking OpenAI LLM");
        return "OpenAI Response";
    }
}
