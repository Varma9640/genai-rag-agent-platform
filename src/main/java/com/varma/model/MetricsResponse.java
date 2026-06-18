package com.varma.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsResponse {
    private long totalQuestions;
    private long totalDocuments;
    private long totalPromptAudits;
    private long totalChatSessions;
}
