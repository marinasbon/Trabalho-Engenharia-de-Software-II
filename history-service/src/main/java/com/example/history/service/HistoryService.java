package com.example.history.service;

import com.example.history.model.HistoryEntry;
import com.example.history.repository.HistoryRepository;
import com.example.history.model.ConversionEvent;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Consumidor de eventos de conversão.  Quando uma mensagem chega na fila,
 * converte o objeto e persiste no banco.
 */
@Service
public class HistoryService {
    @Autowired
    private HistoryRepository repository;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "conversion.queue", durable = "true"),
            exchange = @Exchange(value = "conversion.exchange", type = "topic"),
            key = "conversion.routing"))
    public void receiveConversion(ConversionEvent event) {
        HistoryEntry entry = new HistoryEntry(
                null,
                event.getUser(),
                event.getFrom(),
                event.getTo(),
                event.getAmount(),
                event.getRate(),
                event.getConverted(),
                event.getTimestamp() != null ? event.getTimestamp() : Instant.now());
        repository.save(entry);
    }
}