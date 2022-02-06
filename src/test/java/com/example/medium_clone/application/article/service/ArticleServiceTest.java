package com.example.medium_clone.application.article.service;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.service.ProfileService;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ProfileService profileService;
    @Mock
    private Slugify slugify;

    @Test
    public void testCreate() {
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

        // mocking
        Profile profile = mock(Profile.class);
        Article article = mock(Article.class);
        Long fakeId = 1L;

        when(profileService.findByUsername(any(String.class))).thenReturn(profile);
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        when(article.getId()).thenReturn(fakeId);
        when(slugify.slugify(any(String.class))).thenReturn("slug");

        // when
        Long createdId = articleService.create(dto);

        // then
        assertThat(createdId).isEqualTo(fakeId);
    }
}