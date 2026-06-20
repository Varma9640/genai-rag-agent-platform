package com.varma.domain.service;

import com.varma.agent.AgentOrchestrator;
import com.varma.model.AgentQueryRequest;
import com.varma.model.AgentQueryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentOrchestrator agentOrchestrator;

    public AgentQueryResponse query(AgentQueryRequest request) {
        String answer = agentOrchestrator.execute(request.getQuestion());

        return AgentQueryResponse.builder()
                .question(request.getQuestion())
                .answer(answer)
                .build();
    }
}
