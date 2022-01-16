package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testJoin() {
        // given
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("name");
        dto.setPassword("password");
        dto.setEmail("name@test.com");

        User user = mock(User.class);
        Long fakeId = 1L;

        // mocking
        when(user.getId()).thenReturn(fakeId);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        Long joinId = userService.join(dto);

        // then
        Assertions.assertThat(joinId).isEqualTo(fakeId);
    }
}