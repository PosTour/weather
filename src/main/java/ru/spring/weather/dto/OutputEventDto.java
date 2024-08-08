package ru.spring.weather.dto;

import java.util.UUID;

public record OutputEventDto(
        UUID id,
        String city,
        String type,
        long chatId
) {}
