package com.example.medium_clone.application.article.controller;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleProjection;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.article.service.ArticleService;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.exception.ProfileNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean ArticleService articleService;
    @MockBean ArticleRepository articleRepository;
    private final Slugify slugify = new Slugify();
    private final String commonPath = "/api/articles";

    @Test
    public void testCreateArticle() throws Exception {
        // given
        ArticleCreateDto dto = getArticleCreateDto();
        Profile author = Profile.createProfile("bio", dto.getUsername());
        Article article = Article.createArticle(author, dto.getTitle(), dto.getBody(), dto.getDescription(), slugify, 10, 200);

        String requestBody = objectMapper.writeValueAsString(dto);

        // mocking
        Long fakeId = 1L;
        when(articleService.create(any(ArticleCreateDto.class))).thenReturn(fakeId);
        when(articleRepository.findById(fakeId)).thenReturn(Optional.of(article));

        // then
        mockMvc.perform(post(commonPath)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetArticle() throws Exception {
        // given
        ArticleCreateDto dto = getArticleCreateDto();
        Profile author = Profile.createProfile("bio", dto.getUsername());
        Article article = Article.createArticle(author, dto.getTitle(), dto.getBody(), dto.getDescription(), slugify, 10, 200);
        String slug = article.getSlug();

        // mocking
        when(articleRepository.findBySlugFetchAuthor(slug)).thenReturn(Optional.of(article));

        // then
        mockMvc.perform(get(commonPath + "/" + slug))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetArticleNotFound() throws Exception {
        // given
        String slug = "not-found-123abc";

        // mocking
        when(articleRepository.findBySlugFetchAuthor(slug)).thenReturn(Optional.empty());

        // then
        mockMvc.perform(get(commonPath + "/" + slug))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateArticleAuthorNotFound() throws Exception {
        // given
        ArticleCreateDto dto = getArticleCreateDto();
        String requestBody = objectMapper.writeValueAsString(dto);

        // mocking
        when(articleService.create(any(ArticleCreateDto.class))).thenThrow(ProfileNotFoundException.class);

        // then
        mockMvc.perform(post(commonPath)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetArticles() throws Exception {
        // given
        String authorName = "test";
        int offset = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<ArticleProjection> page = mock(Page.class);

        // mocking
        when(articleRepository.findAllByAuthorUsername(pageable, authorName)).thenReturn(page);

        // then
        mockMvc.perform(get(commonPath).param("authorName", authorName)
                        .param("pageSize", Integer.toString(pageSize))
                        .param("offset", Integer.toString(offset)))
                .andExpect(status().isOk());
    }

    private ArticleCreateDto getArticleCreateDto() {
        String username = "username";
        String title = "title";
        String body = "body";
        String desc = "";

        ArticleCreateDto dto = new ArticleCreateDto();
        dto.setUsername(username);
        dto.setTitle(title);
        dto.setBody(body);
        dto.setDescription(desc);

        return dto;
    }

}
