package ru.spring.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.model.User;

import java.util.UUID;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", expression = "java(userId)")
    User userDtoToUser(UserDto userDto, UUID userId);
}