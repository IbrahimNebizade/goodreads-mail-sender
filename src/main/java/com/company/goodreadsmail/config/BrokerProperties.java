package com.company.goodreadsmail.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rabbit")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrokerProperties {

    private List<Letter> brokers = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Letter {
        private String exchange;
        private String routingKey;
        private String queue;
        private boolean deadLetter;
    }

}