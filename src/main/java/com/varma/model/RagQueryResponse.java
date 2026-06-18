package com.varma.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "RAG Query Response")
public class RagQueryResponse {
    @Schema(description = "User Question")
    private String question;
    @Schema(description = "Generated Answer")
    private String answer;
    private String route;
    private List<SourceResponse> sources;
}
