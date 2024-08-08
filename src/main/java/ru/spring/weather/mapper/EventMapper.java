package ru.spring.weather.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.spring.weather.dto.InputEventDto;
import ru.spring.weather.dto.OutputEventDto;
import ru.spring.weather.model.Event;
import ru.spring.weather.model.User;
import ru.spring.weather.service.UserService;

import java.util.List;

@Mapper
public interface EventMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "user", source = "chatId", qualifiedByName = "getUserByChatId")
    @Mapping(target = "type", expression = "java(Event.Type.getTypeByString(evenDto.type))")
    Event eventDtoToEvent(InputEventDto eventDto);

    List<OutputEventDto> eventsToOutputEventDtos(List<Event> events);

    @Named("getUserByChatId")
    default User getUserByChatId(long chatId, @Context UserService userService) {
        return userService.getUserByChatId(chatId).get();
    }
}
