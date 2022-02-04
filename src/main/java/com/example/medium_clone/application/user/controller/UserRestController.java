package com.example.medium_clone.application.user.controller;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import com.example.medium_clone.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody UserRegisterDto dto) {
        Long memberId = userService.join(dto);
        return userRepository.findById(memberId).orElseThrow(IllegalStateException::new);
    }
}
