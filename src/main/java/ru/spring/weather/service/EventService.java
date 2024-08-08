package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.InputEventDto;
import ru.spring.weather.mapper.EventMapper;
import ru.spring.weather.model.Event;
import ru.spring.weather.repository.EventRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public void saveEvent(InputEventDto eventDto) {
        var event = eventMapper.eventDtoToEvent(eventDto);
        eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEventsByUser(UUID userId) {
        return eventRepository.getEventsByUserId(userId);
    }

    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }
}
