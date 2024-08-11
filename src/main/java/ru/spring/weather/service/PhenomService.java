package ru.spring.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.mapper.PhenomMapper;
import ru.spring.weather.model.Phenom;
import ru.spring.weather.model.User;
import ru.spring.weather.repository.PhenomRepository;
import ru.spring.weather.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PhenomService {

    private final PhenomRepository phenomRepository;
    private final UserService userService;
    private final PhenomMapper phenomMapper;

    public void savePhenom(InputPhenomDto phenomDto) {
        var event = phenomMapper.phenomDtoToPhenom(phenomDto);
        phenomRepository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Phenom> getAllPhenomsByUser(long chatId) {
        Optional<User> user = userService.getUserByChatId(chatId);
        if (user.isPresent()) {
            return phenomRepository.getPhenomsByUserId(user.get().getId());
        } else {
            return Collections.emptyList();
        }
    }

    public void deletePhenom(UUID eventId) {
        phenomRepository.deleteById(eventId);
    }
}
