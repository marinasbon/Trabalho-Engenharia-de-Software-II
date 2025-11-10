package com.example.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

/**
 * Serviço de histórico: consome eventos de conversão de moeda e persiste no banco.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
public class HistoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoryServiceApplication.class, args);
    }
}