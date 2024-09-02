package ru.spring.weather.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.dto.OutputPhenomDto;
import ru.spring.weather.model.Phenom;
import ru.spring.weather.service.UserService;

import java.util.List;

@Mapper
public interface PhenomMapper {
    @Mapping(target = "user", expression = "java(userService.getUserByChatId(phenomDto.chatId()).get())")
    @Mapping(target = "type", expression = "java(Phenom.Type.getTypeByString(phenomDto.type()))")
    Phenom inputPhenomDtoToPhenom(InputPhenomDto phenomDto, @Context UserService userService);

    List<OutputPhenomDto> phenomsToOutputPhenomDtos(List<Phenom> phenoms);
}
