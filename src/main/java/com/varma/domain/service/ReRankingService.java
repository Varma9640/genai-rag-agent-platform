package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RetrievalResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ReRankingService {
    public List<DocumentChunk> reRank(List<RetrievalResult> results) {

        return results.stream()
                .sorted(Comparator.comparingDouble(RetrievalResult::getFinalScore).reversed())
                .limit(5)
                .map(RetrievalResult::getChunk)
                .toList();
    }
}
