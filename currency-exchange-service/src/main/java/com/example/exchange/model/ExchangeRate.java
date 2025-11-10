package com.example.exchange.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidade que representa uma taxa de conversão de moeda.
 */
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String currencyFrom;
    @Column(nullable = false)
    private String currencyTo;
    @Column(nullable = false)
    private BigDecimal rate;

    public ExchangeRate() {}
    public ExchangeRate(Long id, String currencyFrom, String currencyTo, BigDecimal rate) {
        this.id = id;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCurrencyFrom() { return currencyFrom; }
    public void setCurrencyFrom(String currencyFrom) { this.currencyFrom = currencyFrom; }
    public String getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
}