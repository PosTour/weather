package ru.spring.weather.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherCheck {

    @Scheduled(cron = "${weather-check.daily-check-cron}")
    public void checkWeather() {
        // TODO: отправка запроса для получения погодных явлений
        //       вызов метода для отправки уведомления пользователю (при необходимости)
    }
}
