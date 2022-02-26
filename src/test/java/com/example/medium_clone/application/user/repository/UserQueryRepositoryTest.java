package com.example.medium_clone.application.user.repository;

import com.example.medium_clone.application.user.dto.UserRegisterResponseDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserQueryRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserQueryRepository userQueryRepository;

    @Test
    public void testFindUserRegisterResponseDtoById() {
        // given
        Long id = saveUser();

        // when
        UserRegisterResponseDto dto = userQueryRepository.findUserRegisterResponseDtoById(id)
                .orElseThrow(IllegalStateException::new);

        // then
        User savedUser = userRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
        assertThat(dto.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(dto.getUsername()).isEqualTo(savedUser.getProfile().getUsername());
    }

    private Long saveUser() {
        Profile profile = Profile.builder()
                .username("test")
                .bio("")
                .build();

        User user = User.builder()
                .password("password")
                .email("email@test.com")
                .profile(profile)
                .build();

        userRepository.save(user);
        return user.getId();
    }
}