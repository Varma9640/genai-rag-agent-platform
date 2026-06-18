package com.varma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalResult {
    private DocumentChunk chunk;
    private double vectorScore;
    private double keywordScore;
    private double finalScore;
}
