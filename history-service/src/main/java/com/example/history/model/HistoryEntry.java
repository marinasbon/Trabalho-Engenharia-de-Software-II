package com.example.history.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Entidade que armazena o histórico de conversões.
 */
@Entity
@Table(name = "history_entry")
public class HistoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal converted;
    private Instant timestamp;
    public HistoryEntry() {}
    public HistoryEntry(Long id, String user, String currencyFrom, String currencyTo, BigDecimal amount, BigDecimal rate, BigDecimal converted, Instant timestamp) {
        this.id = id;
        this.user = user;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amount = amount;
        this.rate = rate;
        this.converted = converted;
        this.timestamp = timestamp;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getCurrencyFrom() { return currencyFrom; }
    public void setCurrencyFrom(String currencyFrom) { this.currencyFrom = currencyFrom; }
    public String getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public BigDecimal getConverted() { return converted; }
    public void setConverted(BigDecimal converted) { this.converted = converted; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}