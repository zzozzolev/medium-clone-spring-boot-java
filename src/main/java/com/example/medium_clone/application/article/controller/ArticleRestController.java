package com.example.medium_clone.application.article.controller;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.article.repository.ArticleProjection;
import com.example.medium_clone.application.article.repository.ArticleRepository;
import com.example.medium_clone.application.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

    /**
     * Get ArticleProjection page.
     * @return Page<ArticleProjection>
     */
    @GetMapping
    public Page<ArticleProjection> getArticles(@SortDefault(sort="created_date", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false) String authorName) {
        return articleService.getArticles(pageable, authorName);
    }

    /**
     * Get article by path variable slug.
     *
     * @return Article with its author which is Profile.
     */
    @GetMapping("/{slug}")
    public Article getArticle(@PathVariable String slug) {
        return articleRepository.findBySlugFetchAuthor(slug).orElseThrow(NoSuchElementException::new);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    void handleNoSuchElementException(NoSuchElementException exception) {

    }
}
