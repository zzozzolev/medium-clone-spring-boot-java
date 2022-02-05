package com.example.medium_clone.application.article.repository;

import com.example.medium_clone.application.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
