package com.example.medium_clone.application.article.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleCreateDto {

    @NotBlank
    private String username;
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    private String description;
}
