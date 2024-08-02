package ru.spring.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.EventDto;
import ru.spring.weather.mapper.EventMapper;
import ru.spring.weather.model.Event;
import ru.spring.weather.model.User;
import ru.spring.weather.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserService userService;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper, UserService userService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userService = userService;
    }

    @Transactional
    public void saveEvent(EventDto eventDto) {
        var event = eventMapper.eventDtoToEvent(eventDto);
        eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEventsByUser(User user) {
        return eventRepository.getEventsByUser(user);
    }
}
