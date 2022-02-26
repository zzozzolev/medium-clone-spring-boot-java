package com.example.medium_clone.application.user.controller;

import com.example.medium_clone.application.user.dto.ProfileGetDto;
import com.example.medium_clone.application.user.dto.ProfileUpdateDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.exception.ProfileNotFoundException;
import com.example.medium_clone.application.user.repository.ProfileQueryRepository;
import com.example.medium_clone.application.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final ProfileService profileService;
    private final ProfileQueryRepository profileQueryRepository;

    @GetMapping("/{username}")
    public ProfileGetDto getProfile(@PathVariable String username) {
        return profileQueryRepository.findProfileGetDtoByUsername(username).orElseThrow(() -> new ProfileNotFoundException(username));
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String username, @RequestBody ProfileUpdateDto dto) {
        profileService.update(username, dto);
        return ResponseEntity.noContent().build();
    }
}
