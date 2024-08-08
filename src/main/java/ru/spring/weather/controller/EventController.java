package ru.spring.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.weather.dto.InputEventDto;
import ru.spring.weather.dto.OutputEventDto;
import ru.spring.weather.mapper.EventMapper;
import ru.spring.weather.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addEvent(@RequestBody InputEventDto eventDto) {
        eventService.saveEvent(eventDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/by/${user_id}")
    List<OutputEventDto> getAllEventsByUserId(@PathVariable("user_id") UUID userId) {
        var events = eventService.getAllEventsByUser(userId);
        return eventMapper.eventsToOutputEventDtos(events);
    }

    @DeleteMapping("/delete/${id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
