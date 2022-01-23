package com.example.medium_clone.application.user.controller;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import com.example.medium_clone.application.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean UserService userService;
    @MockBean UserRepository userRepository;
    private final String commonPath = "/api/users";

    @Test
    public void testRegisterUser() throws Exception {
        // given
        String username = "test";
        String password = "password";
        String email = "test@email.com";
        String bio = "";

        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setEmail(email);
        String body = objectMapper.writeValueAsString(dto);

        Profile profile = Profile.createProfile(bio, username);
        User user = User.createUser(password, email, profile);
        Long fakeId = 1L;

        // mocking
        when(userService.join(any(UserRegisterDto.class))).thenReturn(fakeId);
        when(userRepository.findById(fakeId)).thenReturn(Optional.of(user));

        // then
        mockMvc.perform(post(commonPath + "/register")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}