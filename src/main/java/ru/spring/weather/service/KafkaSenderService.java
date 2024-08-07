package ru.spring.weather.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToBot(String message) {
        kafkaTemplate.send("bot", message);
    }
}
