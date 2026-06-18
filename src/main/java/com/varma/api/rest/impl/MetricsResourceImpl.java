package com.varma.api.rest.impl;

import com.varma.api.MetricsResource;
import com.varma.domain.service.DashboardService;
import com.varma.model.MetricsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricsResourceImpl implements MetricsResource {
    private final DashboardService dashboardService;

    @Override
    public ResponseEntity<MetricsResponse> getMetrics() {
        return ResponseEntity.ok(dashboardService.getMetrics());
    }
}
