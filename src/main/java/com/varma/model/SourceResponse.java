package com.varma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceResponse {
    private String documentName;
    private Integer chunkNumber;
}
