package ru.spring.weather.dto;

public record EventDto(
        String type,
        long chatId
) {}
