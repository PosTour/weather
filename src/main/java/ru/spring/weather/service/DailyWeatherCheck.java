package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.spring.weather.client.EventFeignClient;

@Component
@RequiredArgsConstructor
public class DailyWeatherCheck {
    private final EventFeignClient eventFeignClient;
    private final KafkaSenderService kafkaSenderService;

    @Scheduled(cron = "${weather-check.daily-check-cron}")
    public void checkWeather() {

    }

    private void notifyUser() {

    }
}
