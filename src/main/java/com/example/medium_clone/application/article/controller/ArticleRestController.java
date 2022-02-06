package com.example.medium_clone.application.article.controller;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.article.service.ArticleService;
import com.example.medium_clone.application.user.entity.Profile;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleRestController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody ArticleCreateDto dto) {
        Long articleId = articleService.create(dto);
        return articleRepository.findById(articleId).orElseThrow(IllegalStateException::new);
    }

}
