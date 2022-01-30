package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.ProfileUpdateDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    public void testUpdateUsername() {
        // given
        String username = "test";
        String updatedUsername = "test2";
        Profile profile = getProfile(username);
        ProfileUpdateDto dto = new ProfileUpdateDto();
        dto.setUsername(updatedUsername);

        // mocking
        setRepoMocking(username, profile);

        // when
        profileService.update(username, dto);

        // then
        assertThat(profile.getUsername()).isEqualTo(updatedUsername);
    }

    private void setRepoMocking(String username, Profile profile) {
        when(profileRepository.findByUsername(username)).thenReturn(Optional.of(profile));
    }

    private Profile getProfile(String username){
        return Profile.createProfile("", username);
    }
}