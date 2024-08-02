package ru.spring.weather.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "eventFeignClient", url = "")
public class EventFeignClient {
}
