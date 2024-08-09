package ru.spring.weather.dto;

import java.util.List;

public record ForecastDto(
        String city,
        List<String> phenoms
) {}
