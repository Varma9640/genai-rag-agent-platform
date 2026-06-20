package com.varma.api.rest.impl;

import com.varma.domain.service.DashboardService;
import com.varma.model.MetricsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MetricsResourceImplTest {
    private MockMvc mockMvc;

    @Mock
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(new MetricsResourceImpl(dashboardService))
                .build();
    }

    @Test
    void testGetMetrics() throws Exception {

        MetricsResponse response = MetricsResponse.builder()
                .totalQuestions(10)
                .totalDocuments(5)
                .totalPromptAudits(2)
                .totalChatSessions(4)
                .build();

        when(dashboardService.getMetrics()).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalQuestions")
                        .value(10))
                .andExpect(jsonPath("$.totalDocuments")
                        .value(5));

        verify(dashboardService).getMetrics();
    }
}
