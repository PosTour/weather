package ru.spring.weather.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.mapper.UserMapper;
import ru.spring.weather.model.User;
import ru.spring.weather.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;

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

    @Transactional
    public List<User> getUsersWithPhenoms() {
        Session session = entityManager.unwrap(Session.class);

        return (List<User>) session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.phenoms");
    }
}
