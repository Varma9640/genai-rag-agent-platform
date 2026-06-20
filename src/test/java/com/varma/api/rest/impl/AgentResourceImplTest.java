package com.varma.api.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varma.domain.service.AgentService;
import com.varma.model.AgentQueryRequest;
import com.varma.model.AgentQueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgentResourceImplTest {
    private MockMvc mockMvc;
    @Mock
    private AgentService agentService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(
                new AgentResourceImpl(agentService)).build();
    }

    @Test
    void testQuery() throws Exception {

        AgentQueryRequest request = new AgentQueryRequest();
        request.setQuestion("Explain Java");
        AgentQueryResponse response = AgentQueryResponse.builder()
                .question("Explain Java")
                .answer("Java Answer")
                .build();

        when(agentService.query(any())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/agent/execute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.question")
                                .value("Explain Java"))
                .andExpect(
                        jsonPath("$.answer")
                                .value("Java Answer"));
        verify(agentService).query(any());
    }
}
