package com.example.conversion.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para consumir o serviço de câmbio via Eureka.
 */
@FeignClient(name = "currency-exchange-service", configuration = com.example.conversion.config.FeignConfig.class)
public interface ExchangeClient {

    @GetMapping("/exchange/{from}/{to}")
    BigDecimal getRate(@PathVariable("from") String from,
            @PathVariable("to") String to);
}
