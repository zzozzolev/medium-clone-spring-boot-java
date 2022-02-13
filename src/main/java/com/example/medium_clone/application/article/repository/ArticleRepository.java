package com.example.medium_clone.application.article.repository;

import com.example.medium_clone.application.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a left join fetch a.author where a.slug = :slug")
    Optional<Article> findBySlugFetchAuthor(@Param("slug") String slug);

    Page<ArticleProjection> findAllByAuthorUsername(Pageable pageable, @Param("authorName") String authorName);

    @Query(value = "select a.article_id as id, a.title, a.slug, a.description, p.username as authorUsername "
        + "from article a left join profile p",
        countQuery = "select count(*) from article",
        nativeQuery = true)
    Page<ArticleProjection> findAllProjection(Pageable pageable);
}
