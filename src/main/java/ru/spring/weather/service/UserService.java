package ru.spring.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.mapper.UserMapper;
import ru.spring.weather.model.User;
import ru.spring.weather.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        var user = userMapper.userDtoToUser(userDto);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByChatId(long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
