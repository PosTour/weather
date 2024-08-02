package ru.spring.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.weather.model.Event;
import ru.spring.weather.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> getEventsByUser(User user);
}
