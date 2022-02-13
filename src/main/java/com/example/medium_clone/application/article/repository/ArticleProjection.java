package com.example.medium_clone.application.article.repository;

public interface ArticleProjection {

    Long getId();
    String getTitle();
    String getSlug();
    String getDescription();
    String getAuthorUsername();

}
