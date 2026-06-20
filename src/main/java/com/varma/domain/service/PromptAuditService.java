package com.varma.domain.service;

import com.varma.domain.entity.PromptAudit;
import com.varma.domain.repository.PromptAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PromptAuditService {
    private final PromptAuditRepository promptAuditRepository;

    public void save(String question, String context, String prompt, String answer) {

        PromptAudit audit = PromptAudit.builder()
                .question(question)
                .context(context)
                .prompt(prompt)
                .answer(answer)
                .createdDate(LocalDateTime.now())
                .build();
        promptAuditRepository.save(audit);
    }
}
