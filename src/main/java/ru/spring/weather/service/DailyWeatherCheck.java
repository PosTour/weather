package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.spring.weather.client.EventFeignClient;
import ru.spring.weather.dto.ForecastDto;
import ru.spring.weather.model.User;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DailyWeatherCheck {
    private final EventFeignClient eventFeignClient;
    private final KafkaSenderService kafkaSenderService;
    private final UserService userService;

    @Scheduled(cron = "${weather-check.daily-check-cron}")
    public void checkWeather() {
        var users = userService.getUsersWithEvents();

        var forecastsRequest = getForecastsForRequest(users);
        var forecastsResponse = eventFeignClient.checkEvents(forecastsRequest);

        notifyUsers(forecastsResponse, users);
    }

    private List<ForecastDto> getForecastsForRequest(List<User> users) {
        Map<String, Set<String>> forecastMap = new HashMap<>();

        for (var user : users) {
            for (var event : user.getEvents()) {
                forecastMap.computeIfAbsent(
                        event.getCity(), k -> new HashSet<>()).add(event.getType().toString());
            }
        }

        List<ForecastDto> forecastDtos = new ArrayList<>();
        forecastMap.forEach((city, events) -> forecastDtos.add(new ForecastDto(city, new ArrayList<>(events))));

        return forecastDtos;
    }

    // TODO: Желательно подумать над оптимизацией этого кошмара

    private void notifyUsers(List<ForecastDto> forecastDtos, List<User> users) {
        forecastDtos.forEach(forecastDto -> {
            users.forEach(user -> {
                user.getEvents().forEach(event -> {
                    if (forecastDto.events().contains(event.getType().toString())
                            && Objects.equals(event.getCity(), forecastDto.city())) {
                        kafkaSenderService.sendToBot(
                                user.getChatId() + " " +
                                forecastDto.city() + " " +
                                event.getType());
                    }
        });});});
    }
}
