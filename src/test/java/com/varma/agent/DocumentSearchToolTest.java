package com.varma.agent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentSearchToolTest {

    @Test
    void testSearch() {
        DocumentSearchTool tool = new DocumentSearchTool();
        String result = tool.search("Spring");
        assertEquals("Document Search Result", result);
    }
}
