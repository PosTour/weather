package ru.spring.weather.dto;

import java.util.UUID;

public record OutputPhenomDto(
        UUID id,
        String city,
        String type,
        long chatId
) {}
