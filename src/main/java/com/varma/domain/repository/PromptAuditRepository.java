package com.varma.domain.repository;

import com.varma.domain.entity.PromptAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptAuditRepository extends JpaRepository<PromptAudit, Long> {
}
