package ru.spring.weather.dto;

public record InputPhenomDto(
        String city,
        String type,
        long chatId
) {}
