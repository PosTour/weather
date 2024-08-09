package ru.spring.weather.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.spring.weather.dto.InputPhenomDto;
import ru.spring.weather.dto.OutputPhenomDto;
import ru.spring.weather.model.Phenom;
import ru.spring.weather.model.User;
import ru.spring.weather.service.UserService;

import java.util.List;

@Mapper
public interface PhenomMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "user", source = "chatId", qualifiedByName = "getUserByChatId")
    @Mapping(target = "type", expression = "java(Phenom.Type.getTypeByString(phenomDto.type))")
    Phenom phenomDtoToPhenom(InputPhenomDto phenomDto);

    List<OutputPhenomDto> phenomsToOutputPhenomDtos(List<Phenom> phenoms);

    @Named("getUserByChatId")
    default User getUserByChatId(long chatId, @Context UserService userService) {
        return userService.getUserByChatId(chatId).get();
    }
}
