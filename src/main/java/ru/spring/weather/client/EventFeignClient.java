package ru.spring.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import ru.spring.weather.dto.ForecastDto;

import java.util.List;

@FeignClient(value = "eventFeignClient", url = "")
@Component
public interface EventFeignClient {
    @GetMapping(value = "")
    List<ForecastDto> checkEvents(List<ForecastDto> forecastDtos);
}
