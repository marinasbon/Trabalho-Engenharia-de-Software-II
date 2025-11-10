package com.example.conversion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Evento enviado para a fila de mensagens após uma conversão.
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
    public ConversionEvent(String user, String from, String to, BigDecimal amount, BigDecimal rate, BigDecimal converted, Instant timestamp) {
        this.user = user;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.converted = converted;
        this.timestamp = timestamp;
    }
    public String getUser() { return user; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getRate() { return rate; }
    public BigDecimal getConverted() { return converted; }
    public Instant getTimestamp() { return timestamp; }
    public void setUser(String user) { this.user = user; }
    public void setFrom(String from) { this.from = from; }
    public void setTo(String to) { this.to = to; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public void setConverted(BigDecimal converted) { this.converted = converted; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}