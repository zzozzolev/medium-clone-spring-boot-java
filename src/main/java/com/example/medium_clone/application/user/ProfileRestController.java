package com.example.medium_clone.application.user;

import com.example.medium_clone.application.user.dto.ProfileUpdateDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final ProfileRepository profileRepository;

    @GetMapping("/{username}")
    public Profile getProfile(@PathVariable String username) {
        return profileRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String username, @RequestBody ProfileUpdateDto dto) {
        return ResponseEntity.noContent().build();
    }
}
