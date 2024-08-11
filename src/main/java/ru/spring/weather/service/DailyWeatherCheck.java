package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.spring.weather.client.PhenomFeignClient;
import ru.spring.weather.dto.ForecastDto;
import ru.spring.weather.dto.NotificationDto;
import ru.spring.weather.model.User;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DailyWeatherCheck {
    private final PhenomFeignClient phenomFeignClient;
    private final KafkaSenderService kafkaSenderService;
    private final UserService userService;

    @Scheduled(cron = "${weather-check.daily-check-cron}")
    public void checkWeather() {
        var users = userService.getUsersWithPhenoms();

        var forecastsRequest = getForecastsForRequest(users);
        var forecastsResponse = phenomFeignClient.checkPhenoms(forecastsRequest);

        notifyUsers(forecastsResponse, users);
    }

    private List<ForecastDto> getForecastsForRequest(List<User> users) {
        Map<String, Set<String>> forecastMap = new HashMap<>();

        for (var user : users) {
            for (var phenom : user.getPhenoms()) {
                forecastMap.computeIfAbsent(
                        phenom.getCity(), k -> new HashSet<>()).add(phenom.getType().toString());
            }
        }

        List<ForecastDto> forecastDtos = new ArrayList<>();
        forecastMap.forEach((city, phenoms) -> forecastDtos.add(new ForecastDto(city, new ArrayList<>(phenoms))));

        return forecastDtos;
    }

    // TODO: Желательно подумать над оптимизацией этого кошмара

    private void notifyUsers(List<ForecastDto> forecastDtos, List<User> users) {
        forecastDtos.forEach(forecastDto -> {
            users.forEach(user -> {
                user.getPhenoms().forEach(phenom -> {
                    if (forecastDto.phenoms().contains(phenom.getType().toString())
                            && Objects.equals(phenom.getCity(), forecastDto.city())) {
                        kafkaSenderService.sendToBot(
                                new NotificationDto(
                                    user.getChatId(),
                                    forecastDto.city(),
                                    phenom.getType().toString()));
                    }
        });});});
    }
}
