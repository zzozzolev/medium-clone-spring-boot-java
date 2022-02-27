package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserRepository userRepository;
    private String ENCRYPTED_PASSWORD = "$2a$12$4.9yy0s7wNrNSuoZkeZiCubaF0UbyMzsMRhwjgv.4KXgNqTopRkg6";

    @Test
    public void testJoin() {
        // given
        UserRegisterDto dto = getDto();

        User user = mock(User.class);
        Long fakeId = 1L;

        // mocking
        when(user.getId()).thenReturn(fakeId);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        Long joinId = userService.join(dto);

        // then
        assertThat(joinId).isEqualTo(fakeId);
    }

    @Test
    public void testUserPasswordEncrypted() {
        // given
        UserRegisterDto dto = getDto();

        // mocking
        when(bCryptPasswordEncoder.encode(dto.getPassword())).thenReturn(ENCRYPTED_PASSWORD);

        // when
        User createdUser = userService.createUser(dto);

        // then
        assertThat(createdUser.getPassword()).isNotEqualTo(dto.getPassword());
    }

    private UserRegisterDto getDto() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("name");
        dto.setPassword("password");
        dto.setEmail("name@test.com");

        return dto;
    }
}