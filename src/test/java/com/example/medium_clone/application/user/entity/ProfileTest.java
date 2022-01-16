package com.example.medium_clone.application.user.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    public void testCreateProfile() {
        // given
        String bio = "";
        String username = "name";

        // when
        Profile createdProfile = Profile.createProfile(bio, username);

        // then
        Assertions.assertThat(createdProfile.getBio()).isEqualTo(bio);
        Assertions.assertThat(createdProfile.getUsername()).isEqualTo(username);
    }

}