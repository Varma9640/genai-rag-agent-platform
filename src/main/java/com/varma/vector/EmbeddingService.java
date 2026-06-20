package com.varma.vector;

import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmbeddingService {
    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    public float[] generateEmbedding(String text) {

        float[] embedding = ollamaEmbeddingModel
                        .embed(text)
                        .content()
                        .vector();

        log.info("Embedding Dimension : {}", embedding.length);
        return embedding;
    }
}
