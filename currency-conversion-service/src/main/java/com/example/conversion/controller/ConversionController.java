package com.example.conversion.controller;

import com.example.conversion.model.ConversionResult;
import com.example.conversion.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.security.Principal;

/**
 * Controlador REST para solicitações de conversão.
 */
@RestController
@RequestMapping("/convert")
public class ConversionController {
    @Autowired
    private ConversionService service;

    @GetMapping("/{from}/{to}/{amount}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ConversionResult> convert(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable BigDecimal amount,
                                                    Principal principal) {
        ConversionResult result = service.convert(principal.getName(), from, to, amount);
        return ResponseEntity.ok(result);
    }
}