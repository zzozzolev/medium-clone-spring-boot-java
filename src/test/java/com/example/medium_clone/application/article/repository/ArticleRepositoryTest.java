package com.example.medium_clone.application.article.repository;

import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.repository.ProfileRepository;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}
