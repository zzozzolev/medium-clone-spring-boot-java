package com.example.medium_clone.application.article.service;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleProjection;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.service.ProfileService;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    /*
    create에 특별한 비즈니스 로직이 없어 mocking 로직만 들어간다.
    따라서 테스트 되는 건 없다. 이후 로직이 추가되면 테스트될 예정.
     */
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

    @Test
    public void testGetArticlesAuthorNameNull() {
        // given
        String authorName = null;
        int offset = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<ArticleProjection> page = mock(Page.class);

        // mocking
        when(articleRepository.findAllProjection(pageable)).thenReturn(page);

        // then
        articleService.getArticles(pageable, authorName);
    }

    @Test
    public void testGetArticlesAuthorName() {
        // given
        String authorName = "test";
        int offset = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<ArticleProjection> page = mock(Page.class);

        // mocking
        when(articleRepository.findAllByAuthorUsername(pageable, authorName)).thenReturn(page);

        // then
        articleService.getArticles(pageable, authorName);
    }
}
