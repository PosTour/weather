package ru.spring.weather.mapper;

import org.mapstruct.Mapper;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.model.User;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
}