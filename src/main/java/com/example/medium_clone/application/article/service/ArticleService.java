package com.example.medium_clone.application.article.service;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.service.ProfileService;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
// Default transaction mode is read only.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ProfileService profileService;
    private final ArticleRepository articleRepository;
    private final Slugify slugify;
    private static final int RANDOM_STRING_SIZE = 30;
    // Article.slug length
    private static final int SLUG_MAX_SIZE = 255;

    /**
     * Create a article with given username and article info.
     * @return saved article ID
     */
    @Transactional
    public Long create(ArticleCreateDto dto) {
        Objects.requireNonNull(dto, "ArticleCreateDto");

        // Find a Profile matched with username.
        // Throw an Error when no Profile is matched.

        Objects.requireNonNull(dto.getUsername());
        Profile author = profileService.findByUsername(dto.getUsername());

        // Save Article
        Article article = Article.createArticle(author,
                dto.getTitle(),
                dto.getBody(),
                dto.getDescription(),
                slugify,
                RANDOM_STRING_SIZE,
                SLUG_MAX_SIZE);

        Article savedArticle = articleRepository.save(article);

        return savedArticle.getId();
    }
}
