package com.example.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints de fallback para o circuit breaker. Quando os serviços de destino
 * estiverem indisponíveis, o gateway redireciona para estes handlers e retorna
 * mensagens amigáveis.
 */
@RestController
public class FallbackController {

    @RequestMapping(value = "/fallback/exchange", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<String> exchangeFallback() {
        return ResponseEntity.ok("Serviço de câmbio indisponível. Tente novamente mais tarde.");
    }

    @GetMapping("/fallback/conversion")
    public ResponseEntity<String> conversionFallback() {
        return ResponseEntity.ok("Serviço de conversão indisponível. Tente novamente mais tarde.");
    }
}
