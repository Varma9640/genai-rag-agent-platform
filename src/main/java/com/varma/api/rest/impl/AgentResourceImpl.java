package com.varma.api.rest.impl;

import com.varma.api.AgentResource;
import com.varma.domain.service.AgentService;
import com.varma.model.AgentQueryRequest;
import com.varma.model.AgentQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgentResourceImpl implements AgentResource {
    private final AgentService agentService;

    @Override
    public ResponseEntity<AgentQueryResponse> query(AgentQueryRequest request) {

        return ResponseEntity.ok(agentService.query(request));
    }
}
