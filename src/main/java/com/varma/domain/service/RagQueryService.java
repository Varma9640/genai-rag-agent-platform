package com.varma.domain.service;

import com.varma.model.DocumentChunk;
import com.varma.model.RagQueryRequest;
import com.varma.model.RetrievalResponse;
import com.varma.model.RetrievalResult;
import com.varma.vector.EmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RagQueryService {

    private final EmbeddingService embeddingService;
    private final SimilaritySearchService similaritySearchService;
    private final ReRankingService reRankingService;
    private final ContextBuilderService contextBuilderService;
    private final QueryHistoryService queryHistoryService;
    private final PromptAuditService promptAuditService;
    private final ChatMemoryService chatMemoryService;
    private final MetricsService metricsService;
    private final FallbackLlmService fallbackLlmService;

    public String ask(RagQueryRequest ragQueryRequest) {

        metricsService.incrementQueryCount();

        log.info("Question : {}", ragQueryRequest.getQuestion());

        float[] questionEmbedding =
                embeddingService.generateEmbedding(ragQueryRequest.getQuestion());

        List<RetrievalResult> retrievalResults =
                similaritySearchService.findRelevantChunks(
                        ragQueryRequest.getQuestion(),
                        questionEmbedding);

        List<DocumentChunk> chunks =
                reRankingService.reRank(
                        retrievalResults);

        if (chunks.isEmpty()) {

            log.warn(
                    "No relevant chunks found for question : {}",
                    ragQueryRequest.getQuestion());

            return """
                    I could not find relevant information
                    in the uploaded documents.
                    """;
        }

        String context =
                contextBuilderService.buildContext(chunks);

        log.info(
                "Context Length : {}",
                context.length());

        String conversationMemory =
                chatMemoryService
                        .getRecentConversation(
                                ragQueryRequest.getQuestion());

        log.info(
                "Conversation Memory Length : {}",
                conversationMemory.length());

        String prompt = """
                You are a helpful AI assistant.

                Use only the provided context
                and conversation history.

                Previous Conversation:
                %s

                Context:
                %s

                Question:
                %s

                Answer:
                """
                .formatted(
                        conversationMemory,
                        context,
                        ragQueryRequest.getQuestion()
                );

        log.info("Invoking LLM Provider");

        String answer =
                fallbackLlmService.generateResponse(
                        prompt);

        log.info(
                "Answer Length : {}",
                answer.length());

        promptAuditService.save(
                ragQueryRequest.getQuestion(),
                context,
                prompt,
                answer
        );

        queryHistoryService.save(
                ragQueryRequest.getQuestion(),
                answer
        );

        chatMemoryService.save(ragQueryRequest.getQuestion(), ragQueryRequest.getSessionId(), answer);

        return answer;
    }

    public List<DocumentChunk> retrieveChunks(
            String question) {

        float[] questionEmbedding =
                embeddingService.generateEmbedding(
                        question);

        List<RetrievalResult> retrievalResults =
                similaritySearchService.findRelevantChunks(
                        question,
                        questionEmbedding);

        return reRankingService.reRank(
                retrievalResults);
    }

    public List<RetrievalResponse> debugRetrieval(
            String question) {

        float[] questionEmbedding =
                embeddingService.generateEmbedding(
                        question);

        List<RetrievalResult> retrievalResults =
                similaritySearchService.findRelevantChunks(
                        question,
                        questionEmbedding);

        List<DocumentChunk> chunks =
                reRankingService.reRank(
                        retrievalResults);

        return chunks.stream()
                .map(chunk ->
                        RetrievalResponse.builder()
                                .documentName(
                                        chunk.getDocumentName())
                                .chunkNumber(
                                        chunk.getChunkNumber())
                                .content(
                                        chunk.getContent())
                                .build())
                .toList();
    }
}
