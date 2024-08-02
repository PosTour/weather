package ru.spring.weather.dto;

public record EventDto(
        String eventType,
        long chatId
) {}
