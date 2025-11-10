package com.example.exchange.service;

import com.example.exchange.model.ExchangeRate;
import com.example.exchange.repository.ExchangeRateRepository;
import com.example.exchange.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Camada de serviço para manipular taxas de câmbio.
 */
@Service
public class ExchangeRateService {
    @Autowired
    private ExchangeRateRepository repository;

    public BigDecimal getRate(String from, String to) {
        return repository.findByCurrencyFromAndCurrencyTo(from, to)
                .orElseThrow(() -> new NotFoundException("Rate not found"))
                .getRate();
    }

    public ExchangeRate createRate(String from, String to, BigDecimal rate) {
        return repository.save(new ExchangeRate(null, from, to, rate));
    }

    public ExchangeRate updateRate(Long id, BigDecimal rate) {
        ExchangeRate er = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rate not found"));
        er.setRate(rate);
        return repository.save(er);
    }
}