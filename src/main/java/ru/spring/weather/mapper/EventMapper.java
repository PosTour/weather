package ru.spring.weather.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.spring.weather.dto.EventDto;
import ru.spring.weather.model.Event;
import ru.spring.weather.model.User;
import ru.spring.weather.service.UserService;

@Mapper
public interface EventMapper {

    @Mapping(target = "user", source = "chatId", qualifiedByName = "getUserByChatId")
    @Mapping(target = "type", expression = "java(Event.Type.getTypeByString(evenDto.type))")
    Event eventDtoToEvent(EventDto eventDto);

    @Named("getUserByChatId")
    default User getUserByChatId(long chatId, @Context UserService userService) {
        return userService.getUserByChatId(chatId).get();
    }
}
