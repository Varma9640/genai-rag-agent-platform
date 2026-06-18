package com.varma.api;

import com.varma.model.RagQueryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Validated
@Tag(name = "Streaming Resource")
@RequestMapping("/api/v1/stream")
public interface StreamingResource {
    @Operation(summary = "Stream AI Response", description = "Stream AI response using Server Sent Events")
    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter stream(@Valid @RequestBody RagQueryRequest request);
}
