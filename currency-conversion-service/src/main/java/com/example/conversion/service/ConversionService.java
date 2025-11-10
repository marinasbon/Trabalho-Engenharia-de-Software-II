package com.example.conversion.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.conversion.client.ExchangeClient;
import com.example.conversion.model.ConversionEvent;
import com.example.conversion.model.ConversionResult;

/**
 * Serviço que realiza a conversão de moedas e publica eventos na fila.
 */
@Service
public class ConversionService {
    @Autowired
    private ExchangeClient exchangeClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ConversionResult convert(String user, String from, String to, BigDecimal amount) {
        // A chamada Feign já propaga o token via interceptor
        BigDecimal rate = exchangeClient.getRate(from, to);
        BigDecimal converted = amount.multiply(rate);
        // Envia evento para RabbitMQ
        ConversionEvent event = new ConversionEvent(user, from, to, amount, rate, converted, Instant.now());
        rabbitTemplate.convertAndSend("conversion.exchange", "conversion.routing", event);
        return new ConversionResult(amount, rate, converted);
    }
}
