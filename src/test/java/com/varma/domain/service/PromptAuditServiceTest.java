package com.varma.domain.service;

import com.varma.domain.entity.PromptAudit;
import com.varma.domain.repository.PromptAuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PromptAuditServiceTest {

    @InjectMocks
    private PromptAuditService service;

    @Mock
    private PromptAuditRepository promptAuditRepository;

    @Test
    void testSave() {

        service.save("Question", "Context", "Prompt", "Answer");
        verify(promptAuditRepository).save(any(PromptAudit.class));
    }
}