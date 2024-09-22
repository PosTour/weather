package ru.spring.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.spring.weather.dto.ForecastDto;

import java.util.List;

@FeignClient(value = "phenomFeignClient", url = "${url.forecastAPI}")
public interface PhenomFeignClient {
    @GetMapping(value = "")
    List<ForecastDto> checkPhenoms(List<ForecastDto> forecastDtos);
}
