package com.varma.domain.service;

import com.varma.model.RagQueryRequest;
import com.varma.model.RouteType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgentRoutingService {
    private final QuestionClassifierService questionClassifierService;
    private final RagQueryService ragQueryService;
    private final GeneralChatService generalChatService;

    public String ask(RagQueryRequest ragQueryRequest) {

        RouteType route = questionClassifierService.classify(ragQueryRequest.getQuestion());
        log.info("Selected Route : {}", route);
        if (route == RouteType.RAG) {
            return ragQueryService.ask(ragQueryRequest);
        }
        return generalChatService.ask(ragQueryRequest.getQuestion());
    }
}
