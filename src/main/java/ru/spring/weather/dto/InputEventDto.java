package ru.spring.weather.dto;

public record InputEventDto(
        String city,
        String type,
        long chatId
) {}
