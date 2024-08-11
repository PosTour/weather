package ru.spring.weather.dto;

public record NotificationDto(
    long chatId,
    String city,
    String phenomType
) {}
