package com.varma.api;


import com.varma.api.swagger.AgentQuerySwagger;
import com.varma.model.AgentQueryRequest;
import com.varma.model.AgentQueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "Agent Resource")
@AgentQuerySwagger
@RequestMapping(value = "/api/v1/agent", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AgentResource {
    @Operation(summary = "Execute AI Agent", description = "Execute AI Agent workflow")
    @PostMapping("/execute")
    ResponseEntity<AgentQueryResponse> query(@Valid @RequestBody AgentQueryRequest request);
}
