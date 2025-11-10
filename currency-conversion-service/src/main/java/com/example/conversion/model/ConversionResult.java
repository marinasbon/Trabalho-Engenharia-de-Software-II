package com.example.conversion.model;

import java.math.BigDecimal;

/**
 * Resultado da conversão retornado ao cliente.
 */
public class ConversionResult {
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal converted;
    public ConversionResult() {}
    public ConversionResult(BigDecimal amount, BigDecimal rate, BigDecimal converted) {
        this.amount = amount;
        this.rate = rate;
        this.converted = converted;
    }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
    public BigDecimal getConverted() { return converted; }
    public void setConverted(BigDecimal converted) { this.converted = converted; }
}