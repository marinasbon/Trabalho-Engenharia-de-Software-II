package com.example.exchange.dto;

import java.math.BigDecimal;

/**
 * DTO para atualização de taxa de câmbio.
 */
public class UpdateRateDTO {
    private BigDecimal rate;
    public UpdateRateDTO() {}
    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
}