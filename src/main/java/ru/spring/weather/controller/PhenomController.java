package ru.spring.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.dto.OutputPhenomDto;
import ru.spring.weather.mapper.PhenomMapper;
import ru.spring.weather.service.PhenomService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class PhenomController {

    private final PhenomService phenomService;
    private final PhenomMapper phenomMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addPhenom(@RequestBody InputPhenomDto eventDto) {
        phenomService.savePhenom(eventDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/by/${user_id}")
    List<OutputPhenomDto> getAllPhenomsByUserId(@PathVariable("user_id") UUID userId) {
        var events = phenomService.getAllPhenomsByUser(userId);
        return phenomMapper.phenomsToOutputPhenomDtos(events);
    }

    @DeleteMapping("/delete/${id}")
    public ResponseEntity<Void> deletePhenom(@PathVariable UUID id) {
        phenomService.deletePhenom(id);
        return ResponseEntity.ok().build();
    }
}
