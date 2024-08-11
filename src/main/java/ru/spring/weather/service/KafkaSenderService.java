package ru.spring.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.spring.weather.dto.NotificationDto;

@Component
@RequiredArgsConstructor
public class KafkaSenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    public void sendToBot(NotificationDto notification) {
        try {
            String message = objectMapper.writeValueAsString(notification);
            kafkaTemplate.send(topic, message);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
