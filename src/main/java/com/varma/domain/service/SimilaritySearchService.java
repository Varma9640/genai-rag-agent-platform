package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RetrievalResult;
import com.varma.util.KeywordSearchUtil;
import com.varma.vector.ChromaDbService;
import com.varma.vector.SimilarityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimilaritySearchService {
    private static final double VECTOR_WEIGHT = 0.7;
    private static final double KEYWORD_WEIGHT = 0.3;
    private static final double SIMILARITY_THRESHOLD = 0.40;

    private final ChromaDbService chromaDbService;

    public List<RetrievalResult> findRelevantChunks(String question, float[] queryEmbedding) {

        List<RetrievalResult> results = chromaDbService.getAllChunks()
                        .stream()
                        .map(chunk -> {

                            double vectorScore = SimilarityUtil.cosineSimilarity(queryEmbedding, chunk.getEmbedding());
                            double keywordScore = KeywordSearchUtil.calculateScore(question, chunk.getContent());
                            double finalScore = (vectorScore * VECTOR_WEIGHT)
                                            +
                                            (keywordScore * KEYWORD_WEIGHT);

                            return RetrievalResult.builder()
                                    .chunk(chunk)
                                    .vectorScore(vectorScore)
                                    .keywordScore(keywordScore)
                                    .finalScore(finalScore)
                                    .build();
                        })
                        .filter(result ->
                                result.getFinalScore()
                                        >= SIMILARITY_THRESHOLD)
                        .sorted(Comparator.comparingDouble(RetrievalResult::getFinalScore)
                                        .reversed())
                        .limit(20)
                        .toList();

        results.forEach(result ->
                log.info(
                        "Document : {}, Chunk : {}, VectorScore : {}, KeywordScore : {}, FinalScore : {}",
                        result.getChunk().getDocumentName(),
                        result.getChunk().getChunkNumber(),
                        result.getVectorScore(),
                        result.getKeywordScore(),
                        result.getFinalScore()));

        return results;
    }
}
