package ru.spring.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.weather.dto.UserDto;
import ru.spring.weather.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
        if (userService.getUserByChatId(userDto.chatId()).isPresent()) {
            userService.saveUser(userDto);
        }
        return ResponseEntity.ok().build();
    }
}
