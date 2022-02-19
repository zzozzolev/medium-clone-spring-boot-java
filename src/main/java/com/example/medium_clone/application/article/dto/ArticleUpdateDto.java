package com.example.medium_clone.application.article.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Optional;

@Data
public class ArticleUpdateDto {

    private String title;
    private String body;
    private String description;

    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(this.body);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }
}
