package com.varma.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "RAG Query Request")
public class RagQueryRequest {
    @Schema(description = "Question")
    @NotBlank(message = "Question is mandatory")
    private String question;
    @NotBlank
    private String sessionId;
}
