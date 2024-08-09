package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.mapper.PhenomMapper;
import ru.spring.weather.model.Phenom;
import ru.spring.weather.repository.PhenomRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PhenomService {

    private final PhenomRepository phenomRepository;
    private final PhenomMapper phenomMapper;

    public void savePhenom(InputPhenomDto phenomDto) {
        var event = phenomMapper.phenomDtoToPhenom(phenomDto);
        phenomRepository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Phenom> getAllPhenomsByUser(UUID userId) {
        return phenomRepository.getPhenomsByUserId(userId);
    }

    public void deletePhenom(UUID eventId) {
        phenomRepository.deleteById(eventId);
    }
}
