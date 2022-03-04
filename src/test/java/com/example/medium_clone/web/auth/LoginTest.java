package com.example.medium_clone.web.auth;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    private final String path = "/api/login";
    @Autowired UserService userService;
    private static final String TEST_USER_EMAIL = "test@test.com";
    private static final String TEST_USER_PASSWORD = "test";

    @BeforeAll
    public static void setUp(@Autowired UserService userService) {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setEmail(TEST_USER_EMAIL);
        dto.setUsername("test");
        dto.setPassword(TEST_USER_PASSWORD);
        userService.join(dto);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        // given
        String requestBody = String.format(
                "{\"email\":\"%s\", \"password\":\"%s\"}",
                TEST_USER_EMAIL,
                TEST_USER_PASSWORD);

        // then
        mockMvc.perform(post(path)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginFailEmail() throws Exception {
        // given
        String requestBody = String.format(
                "{\"email\":\"%s\", \"password\":\"%s\"}",
                "wrong",
                TEST_USER_PASSWORD);

        // then
        mockMvc.perform(post(path)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testLoginFailPassword() throws Exception {
        // given
        String requestBody = String.format(
                "{\"email\":\"%s\", \"password\":\"%s\"}",
                TEST_USER_EMAIL,
                "wrong");

        // then
        mockMvc.perform(post(path)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
