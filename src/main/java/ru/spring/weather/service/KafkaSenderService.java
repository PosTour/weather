package ru.spring.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    public KafkaSenderService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToBot(String message) {
        kafkaTemplate.send(topic, message);
    }
}
