package com.example.exchange.dto;

import java.math.BigDecimal;

/**
 * DTO para criação de nova taxa de câmbio.
 */
public class NewRateDTO {
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal rate;
    public NewRateDTO() {}
    public String getCurrencyFrom() { return currencyFrom; }
    public void setCurrencyFrom(String currencyFrom) { this.currencyFrom = currencyFrom; }
    public String getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(String currencyTo) { this.currencyTo = currencyTo; }
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
}