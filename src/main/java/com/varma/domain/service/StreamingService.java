package com.varma.domain.service;

import com.varma.model.RagQueryRequest;
import com.varma.model.StreamingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreamingService {
    private final AgentRoutingService agentRoutingService;

    public SseEmitter streamResponse(RagQueryRequest ragQueryRequest) {

        SseEmitter emitter = new SseEmitter(300000L);
        new Thread(() -> {

            try {
                String answer = agentRoutingService.ask(ragQueryRequest);

                String[] tokens = answer.split(" ");
                for (String token : tokens) {
                    emitter.send(StreamingResponse.builder()
                                    .token(token + " ")
                                    .completed(false)
                                    .build()
                    );

                    Thread.sleep(50);
                }
                emitter.send(StreamingResponse.builder()
                                .completed(true)
                                .build()
                );
                emitter.complete();
            } catch (Exception exception) {
                log.error("Streaming Error", exception);
                emitter.completeWithError(exception);
            }
        }).start();
        return emitter;
    }
}
