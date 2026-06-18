package com.varma.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROMPT_AUDIT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String question;
    @Lob
    private String context;
    @Lob
    private String prompt;
    @Lob
    private String answer;
    private LocalDateTime createdDate;
}
