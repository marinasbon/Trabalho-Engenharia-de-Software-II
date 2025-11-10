package com.example.history.repository;

import com.example.history.model.HistoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório para o histórico de conversões.
 */
public interface HistoryRepository extends JpaRepository<HistoryEntry, Long> {
    List<HistoryEntry> findByUser(String user);
}