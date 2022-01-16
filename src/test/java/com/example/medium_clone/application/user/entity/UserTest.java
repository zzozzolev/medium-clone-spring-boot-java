package com.example.medium_clone.application.user.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserTest {

    @Test
    public void testCreateUser() {
        // given
        String password = "password";
        String email = "name@test.com";
        Profile profile = mock(Profile.class);

        // when
        User createdUser = User.createUser(password, email, profile);

        // then
        assertThat(createdUser.getPassword()).isEqualTo(password);
        assertThat(createdUser.getEmail()).isEqualTo(email);
    }
}