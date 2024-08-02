package ru.spring.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
        if (userService.getUserByChatId(userDto.chatId()).isPresent()) {
            userService.saveUser(userDto);
        }
        return ResponseEntity.ok().build();
    }
}
