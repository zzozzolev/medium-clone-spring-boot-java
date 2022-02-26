package com.example.medium_clone.application.user.controller;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.dto.UserRegisterResponseDto;
import com.example.medium_clone.application.user.repository.UserQueryRepository;
import com.example.medium_clone.application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final UserQueryRepository userQueryRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerUser(@RequestBody @Valid UserRegisterDto dto) {
        Long memberId = userService.join(dto);
        return userQueryRepository.findUserRegisterResponseDtoById(memberId).orElseThrow(IllegalStateException::new);
    }
}
