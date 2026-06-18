package com.varma.domain.service;

import com.varma.domain.entity.QueryHistory;
import com.varma.domain.repository.QueryHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QueryHistoryService {
    private final QueryHistoryRepository queryHistoryRepository;

    public void save(String question, String answer) {

        QueryHistory queryHistory =
                QueryHistory.builder()
                        .question(question)
                        .answer(answer)
                        .createdDate(LocalDateTime.now())
                        .build();
        queryHistoryRepository.save(queryHistory);
    }
}
