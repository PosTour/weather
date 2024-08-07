package ru.spring.weather.dto;

public record EventDto(
        String city,
        String type,
        long chatId
) {}
