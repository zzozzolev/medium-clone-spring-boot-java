package com.example.medium_clone.application.user;

import com.example.medium_clone.application.user.dto.ProfileGetDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {

    @GetMapping("/{username}")
    public ProfileGetDto getProfile(@PathVariable String username) {
        ProfileGetDto dto = new ProfileGetDto();
        dto.setUsername("demo");
        dto.setBio("demo");
        return dto;
    }
}
