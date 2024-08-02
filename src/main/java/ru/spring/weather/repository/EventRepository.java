package ru.spring.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.weather.model.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
