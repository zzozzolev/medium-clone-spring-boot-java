package com.example.medium_clone.application.user.controller;

import com.example.medium_clone.application.user.dto.ProfileUpdateDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import com.example.medium_clone.application.user.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileRestController.class)
class ProfileRestControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean ProfileService profileService;
    @MockBean ProfileRepository profileRepository;
    private final String commonPath = "/api/profiles";

    @Test
    public void testGetProfileOk() throws Exception {
        String username = setTestUserProfile();

        // then
        mockMvc.perform(get(commonPath + "/" + username))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProfileNotFound() throws Exception {
        mockMvc.perform(get(commonPath + "/" + "notFound"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProfile() throws Exception {
        // given
        String username = setTestUserProfile();
        String updateUsername = "test2";
        String updateBio = "bio";
        ProfileUpdateDto dto = new ProfileUpdateDto();
        dto.setUsername(updateUsername);
        dto.setBio(updateBio);
        String body = objectMapper.writeValueAsString(dto);

        // then
        mockMvc.perform(patch(commonPath + "/" + username)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private String setTestUserProfile() {
        // given
        String username = "test";
        String bio = "";

        ProfileUpdateDto dto = new ProfileUpdateDto();
        dto.setUsername(username);

        Profile profile = Profile.builder()
                .bio(bio)
                .username(username)
                .build();

        // mocking
        when(profileRepository.findByUsername(username)).thenReturn(Optional.of(profile));

        return username;
    }
}