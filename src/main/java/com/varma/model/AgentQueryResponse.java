package com.varma.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Agent Query Response")
public class AgentQueryResponse {
    private String question;
    private String answer;
}
