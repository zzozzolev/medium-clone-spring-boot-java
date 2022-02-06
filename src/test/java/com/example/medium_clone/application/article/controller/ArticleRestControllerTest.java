package com.example.medium_clone.application.article.controller;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    private final String commonPath = "/api/articles";

    @Test
    public void testCreateArticle() throws Exception {
        // given
        String username = "username";
        String title = "title";
        String body = "body";
        String desc = "";

        ArticleCreateDto dto = new ArticleCreateDto();
        dto.setUsername(username);
        dto.setTitle(title);
        dto.setBody(body);
        dto.setDescription(desc);

        String requestBody = objectMapper.writeValueAsString(dto);

        // then
        mockMvc.perform(post(commonPath)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

}