package com.example.medium_clone.application.user;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/users")
public class UserRestController {

    @PostMapping("/register")
    public UserRegisterDto registerUser(@RequestBody UserRegisterDto dto) {
        return dto;
    }
}
