package com.example.history.controller;

import com.example.history.model.HistoryEntry;
import com.example.history.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.List;

/**
 * Endpoints de consulta ao histórico de conversões.
 */
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryRepository repository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryEntry>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<HistoryEntry>> mine(Principal principal) {
        return ResponseEntity.ok(repository.findByUser(principal.getName()));
    }
}