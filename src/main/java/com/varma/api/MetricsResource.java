package com.varma.api;

import com.varma.model.MetricsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Tag(name = "Metrics Resource")
@RequestMapping(value = "/api/v1/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MetricsResource {
    @Operation(summary = "Get Dashboard Metrics", description = "Retrieve platform metrics")
    @GetMapping
    ResponseEntity<MetricsResponse> getMetrics();
}
