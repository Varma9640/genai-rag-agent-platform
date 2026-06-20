package com.varma.agent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContextRetrievalToolTest {

    @Test
    void testRetrieve() {
        ContextRetrievalTool tool = new ContextRetrievalTool();
        String result = tool.retrieve("Document");
        assertEquals("Document", result);
    }
}
