package com.varma.domain.service;

import com.varma.model.RouteType;
import org.springframework.stereotype.Service;

@Service
public class QuestionClassifierService {
    public RouteType classify(String question) {

        String q = question.toLowerCase();
        if (q.contains("spring")
                || q.contains("java")
                || q.contains("microservice")
                || q.contains("kubernetes")) {
            return RouteType.RAG;
        }
        return RouteType.GENERAL;
    }
    }
