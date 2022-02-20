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

    @Test
    public void testUpdateBio() {
        // given
        String username = "test";
        String updatedBio = "bio";
        Profile profile = getProfile(username);
        ProfileUpdateDto dto = new ProfileUpdateDto();
        dto.setBio(updatedBio);

        // mocking
        setRepoMocking(username, profile);

        // when
        profileService.update(username, dto);

        // then
        assertThat(profile.getBio()).isEqualTo(updatedBio);
    }

    @Test
    /*
      업데이트하는 필드만 업데이트 되고 나머지 필드는 null로 바뀌지 않는지 테스트한다.
     */
    public void testUpdateNotNull() {
        // given
        String username = "test";
        String updatedBio = "bio";
        Profile profile = getProfile(username);
        ProfileUpdateDto dto = new ProfileUpdateDto();
        dto.setBio(updatedBio);

        // mocking
        setRepoMocking(username, profile);

        // when
        profileService.update(username, dto);

        // then
        // dto의 username이 null일 때, 즉 username은 요청 body에 없을 때
        // 원래의 username이 null로 바뀌지 않는지 확인한다.
        assertThat(dto.getUsername()).isEmpty();
        assertThat(profile.getUsername()).isNotNull();
    }

    private void setRepoMocking(String username, Profile profile) {
        when(profileRepository.findByUsername(username)).thenReturn(Optional.of(profile));
    }

    private Profile getProfile(String username){
        return Profile.builder()
                .bio("")
                .username(username)
                .build();
    }
}