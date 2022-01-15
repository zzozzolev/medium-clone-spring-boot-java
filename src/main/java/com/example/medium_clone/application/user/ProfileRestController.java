package com.example.medium_clone.application.user;

import com.example.medium_clone.application.user.dto.ProfileDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {

    @GetMapping("/{username}")
    public ProfileDto getProfile(@PathVariable String username) {
        ProfileDto dto = new ProfileDto();
        dto.setUsername("demo");
        dto.setBio("demo");
        return dto;
    }

    @PatchMapping("/{username}")
    public ProfileDto updateProfile(@PathVariable String username) {
        ProfileDto dto = new ProfileDto();
        dto.setUsername("demo");
        dto.setBio("demo");
        return dto;
    }
}
