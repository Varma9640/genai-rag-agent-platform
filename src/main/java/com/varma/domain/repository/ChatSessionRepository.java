package com.varma.domain.repository;

import com.varma.domain.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
    List<ChatSession> findBySessionIdOrderByCreatedDateAsc(String sessionId);
}
