package com.example.history.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Representa o evento recebido da fila de mensagens.  Deve possuir os mesmos
 * campos que o evento publicado pelo serviço de conversão.  Não há acoplamento
 * entre serviços; a serialização via RabbitMQ usa a estrutura de campos.
 */
public class ConversionEvent implements Serializable {
    private String user;
    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal converted;
    private Instant timestamp;
    public ConversionEvent() {}
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public BigDecimal getConverted() { return converted; }
    public void setConverted(BigDecimal converted) { this.converted = converted; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}