package com.varma.agent;

import org.springframework.stereotype.Component;

@Component
public class DocumentSearchTool {
    public String search(String question) {
        return "Document Search Result";
    }
}
