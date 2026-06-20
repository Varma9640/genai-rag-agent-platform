package com.varma.domain.service;


import com.varma.domain.entity.QueryHistory;
import com.varma.domain.repository.QueryHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QueryHistoryServiceTest {

    @InjectMocks
    private QueryHistoryService service;

    @Mock
    private QueryHistoryRepository queryHistoryRepository;

    @Test
    void testSave() {

        service.save("What is Spring Boot?", "Spring Boot Answer");
        verify(queryHistoryRepository).save(any(QueryHistory.class));
    }
}
