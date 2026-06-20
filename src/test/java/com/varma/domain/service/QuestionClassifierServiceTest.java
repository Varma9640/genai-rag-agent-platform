package com.varma.domain.service;

import com.varma.model.RouteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionClassifierServiceTest {
    private final QuestionClassifierService service =
            new QuestionClassifierService();

    @Test
    void testClassifyRagQuestion() {

        RouteType result = service.classify("What is Spring Boot?");
        assertEquals(RouteType.RAG, result);
    }

    @Test
    void testClassifyJavaQuestion() {

        RouteType result = service.classify("Explain Java Streams");
        assertEquals(RouteType.RAG, result);
    }

    @Test
    void testClassifyMicroserviceQuestion() {

        RouteType result = service.classify("Explain Microservice Architecture");
        assertEquals(RouteType.RAG, result);
    }

    @Test
    void testClassifyGeneralQuestion() {

        RouteType result = service.classify("How are you today");
        assertEquals(RouteType.GENERAL, result);
    }
}
