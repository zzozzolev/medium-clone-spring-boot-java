package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.ProfileUpdateDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.exception.ProfileNotFoundException;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
// Default transaction mode is read only.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Long update(String username, ProfileUpdateDto dto) {
        Profile profile = profileRepository.findByUsername(username).orElseThrow(
                () -> new ProfileNotFoundException(username)
        );

        dto.getUsername().ifPresent(profile::changeUsername);
        dto.getBio().ifPresent(profile::changeBio);

        return profile.getId();
    }

    /**
     * Find a Profile matched with username.
     * @return Profile instance.
     */
    public Profile findByUsername(String username) {
        Objects.requireNonNull(username);
        return profileRepository.findByUsername(username).orElseThrow(
                () -> new ProfileNotFoundException(username)
        );
    }
}
