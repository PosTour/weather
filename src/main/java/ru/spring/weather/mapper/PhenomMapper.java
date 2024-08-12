package ru.spring.weather.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.dto.OutputPhenomDto;
import ru.spring.weather.model.Phenom;
import ru.spring.weather.service.UserService;

import java.util.List;
import java.util.UUID;

@Mapper
public interface PhenomMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "user", expression = "java(userService.getUserByChatId(phenomDto.chatId()).get())")
    @Mapping(target = "type", expression = "java(Phenom.Type.getTypeByString(phenomDto.type()))")
    Phenom phenomDtoToPhenom(InputPhenomDto phenomDto, @Context UserService userService);

    List<OutputPhenomDto> phenomsToOutputPhenomDtos(List<Phenom> phenoms);
}
