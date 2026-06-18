package com.varma.api.rest.impl;

import com.varma.api.StreamingResource;
import com.varma.domain.service.StreamingService;
import com.varma.model.RagQueryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class StreamingResourceImpl implements StreamingResource {
    private final StreamingService streamingService;

    @Override
    public SseEmitter stream(RagQueryRequest request) {
        return streamingService.streamResponse(request);
    }
}
