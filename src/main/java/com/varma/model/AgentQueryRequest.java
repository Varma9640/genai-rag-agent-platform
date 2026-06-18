package com.varma.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Agent Query Request")
public class AgentQueryRequest {
    @NotBlank(message = "Question is mandatory")
    private String question;
}
