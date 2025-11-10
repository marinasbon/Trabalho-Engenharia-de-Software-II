package com.example.exchange.controller;

import com.example.exchange.model.ExchangeRate;
import com.example.exchange.service.ExchangeRateService;
import com.example.exchange.dto.NewRateDTO;
import com.example.exchange.dto.UpdateRateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Controlador REST para operações de câmbio.
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeRateService service;

    @GetMapping("/{from}/{to}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BigDecimal> getRate(@PathVariable String from, @PathVariable String to) {
        return ResponseEntity.ok(service.getRate(from, to));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExchangeRate> create(@RequestBody NewRateDTO dto) {
        ExchangeRate er = service.createRate(dto.getCurrencyFrom(), dto.getCurrencyTo(), dto.getRate());
        return ResponseEntity.status(HttpStatus.CREATED).body(er);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExchangeRate> update(@PathVariable Long id, @RequestBody UpdateRateDTO dto) {
        ExchangeRate er = service.updateRate(id, dto.getRate());
        return ResponseEntity.ok(er);
    }
}