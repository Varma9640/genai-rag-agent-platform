package com.varma.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResult {
    private DocumentChunk documentChunk;
    private double score;
}
