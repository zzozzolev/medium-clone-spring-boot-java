package com.example.medium_clone.application.article.repository;

import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ArticleRepositoryTest {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired ArticleRepository articleRepository;

    @Test
    public void testSaveArticle() {
        Profile profile = Profile.createProfile("test", "test");
        profileRepository.save(profile);

        Article article = Article.createArticle(profile, "test", "Test", "test", new Slugify(), 20, 100);
        articleRepository.save(article);
    }

    @Test
    public void testFindBySlugFetchAuthor() {
        // given
        Profile profile = Profile.createProfile("test", "test");
        profileRepository.save(profile);

        Article article = Article.createArticle(profile, "test", "Test", "test", new Slugify(), 20, 100);
        articleRepository.save(article);

        // when
        Article foundArticle = articleRepository.findBySlugFetchAuthor(article.getSlug()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(foundArticle).isEqualTo(article);
        assertThat(foundArticle.getAuthor()).isEqualTo(profile);
    }

    @Test
    public void testFindAllByAuthor() {
        // given
        String username = "test";
        Profile profile = Profile.createProfile("test", username);
        profileRepository.save(profile);

        for (int i = 0; i < 10; ++i) {
            Article article = Article.createArticle(profile, "test", "Test", "test", new Slugify(), 20, 100);
            articleRepository.save(article);
        }

        int page = 0;
        int size = 5;

        // when
        Page<ArticleProjection> articles = articleRepository.findAllByAuthorUsername(PageRequest.of(page, size), username);

        // then
        assertThat(articles.getNumberOfElements()).isEqualTo(size);
        assertThat(articles.getNumber()).isEqualTo(page);
    }

    @Test
    public void testFindAllProjection() {
        // given
        String username = "test";
        Profile profile = Profile.createProfile("test", username);
        profileRepository.save(profile);

        for (int i = 0; i < 10; ++i) {
            Article article = Article.createArticle(profile, "test", "Test", "test", new Slugify(), 20, 100);
            articleRepository.save(article);
        }

        int page = 0;
        int size = 5;

        // when
        Page<ArticleProjection> articles = articleRepository.findAllProjection(PageRequest.of(page, size));

        // then
        assertThat(articles.getNumberOfElements()).isEqualTo(size);
        assertThat(articles.getNumber()).isEqualTo(page);
    }

}
