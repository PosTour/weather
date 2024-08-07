package ru.spring.weather.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "weatherFeignClient", url = "")
public interface WeatherFeignClient {
}
