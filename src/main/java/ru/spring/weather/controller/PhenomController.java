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
@RequestMapping("/api/phenom")
public class PhenomController {

    private final PhenomService phenomService;
    private final PhenomMapper phenomMapper;

    @PostMapping("/add")
    public ResponseEntity<Void> addPhenom(@RequestBody InputPhenomDto phenomDto) {
        phenomService.savePhenom(phenomDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/by/${user_id}")
    List<OutputPhenomDto> getAllPhenomsByUserId(@PathVariable("chat_id") long chatId) {
        var phenoms = phenomService.getAllPhenomsByUser(chatId);
        return phenomMapper.phenomsToOutputPhenomDtos(phenoms);
    }

    @DeleteMapping("/delete/${id}")
    public ResponseEntity<Void> deletePhenom(@PathVariable("id") UUID id) {
        phenomService.deletePhenom(id);
        return ResponseEntity.ok().build();
    }
}
