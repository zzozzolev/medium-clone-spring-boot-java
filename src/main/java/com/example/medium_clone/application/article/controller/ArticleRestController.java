package com.example.medium_clone.application.article.controller;

import com.example.medium_clone.application.article.dto.ArticleCreateDto;
import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.user.entity.Profile;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleRestController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody ArticleCreateDto dto) {
        // 임시 객체 반환
        // TODO: 없는 유저 처리 필요
        Profile profile = Profile.createProfile("bio", "username");
        return Article.createArticle(profile, "title", "body", "desc", new Slugify(), 10, 100);
    }

}
