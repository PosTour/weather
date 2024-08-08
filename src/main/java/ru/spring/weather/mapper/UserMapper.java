package ru.spring.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.model.User;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    User userDtoToUser(UserDto userDto);
}
