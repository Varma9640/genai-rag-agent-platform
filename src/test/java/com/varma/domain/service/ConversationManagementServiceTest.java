package com.varma.domain.service;

import com.varma.domain.entity.ChatSession;
import com.varma.domain.repository.ChatSessionRepository;
import com.varma.model.ConversationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConversationManagementServiceTest {
    @InjectMocks
    private ConversationManagementService service;

    @Mock
    private ChatSessionRepository repository;

    @Test
    void testGetConversations() {

        ChatSession session = ChatSession.builder()
                .id(1L)
                .question("Java")
                .answer("Java Answer")
                .build();

        when(repository.findAll()).thenReturn(List.of(session));
        List<ConversationResponse> response = service.getConversations();
        assertEquals(1, response.size());
        assertEquals("Java", response.get(0).getQuestion());
    }

    @Test
    void testGetConversation() {

        ChatSession session = ChatSession.builder()
                .id(1L)
                .question("Java")
                .answer("Java Answer")
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(session));
        ConversationResponse response = service.getConversation(1L);
        assertEquals("Java", response.getQuestion());
    }

    @Test
    void testGetConversationNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> service.getConversation(1L));
    }

    @Test
    void testDeleteConversation() {

        service.deleteConversation(1L);
        verify(repository).deleteById(1L);
    }
}
