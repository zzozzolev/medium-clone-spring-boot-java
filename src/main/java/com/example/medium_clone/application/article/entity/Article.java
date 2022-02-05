package com.example.medium_clone.application.article.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import com.example.medium_clone.application.user.entity.Profile;
import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@ToString(of = {"id", "slug"})
public class Article extends BaseTimeEntity {

    public static final int BODY_LENGTH = 255 * 4;

    public static Article createArticle(Profile author, String title, String body, String description, Slugify slugify, int size, int maxSize) {
        Article article = new Article();
        article.setAuthor(author);
        article.title = title;
        article.setSlug(slugify, title, size, maxSize);
        article.body = body;
        article.description = description;

        return article;
    }

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile author;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false, length = BODY_LENGTH)
    private String body;
    private String description;

    /**
     * Set author to this article instance.
     * Add this article to author's article list.
     * @param author Profile instance.
     */
    void setAuthor(Profile author) {
        Objects.requireNonNull(author, "author");
        this.author = author;
        author.getArticles().add(this);
    }

    /**
     * Set slug to this article instance.
     * Slug is generated using title and random alphanumeric.
     * If the sum of slug and random string length is greater than 'maxSize',
     * substring will be applied.
     * @param slugify com.github.slugify instance.
     * @param title article title.
     * @param size Length of random string.
     * @param maxSize Total length of slug and random string.
     * @throws IllegalArgumentException
     */
    void setSlug(Slugify slugify, String title, int size, int maxSize) throws IllegalArgumentException {
        // Validate parameters.
        Objects.requireNonNull(slugify);
        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("title is blank.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size must be greater than zero.");
        }
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than zero.");
        }
        if (size > maxSize) {
            throw new IllegalArgumentException("maxSize must be greater than or equal size.");
        }

        String slug = slugify.slugify(title);
        String randomString = RandomStringUtils.randomAlphanumeric(size);

        int randomHyphenSize = randomString.length() + 1;
        // slug 길이와 랜덤 스트링 길이의 합이 최대 길이보다 큰 경우
        if (slug.length() + randomHyphenSize > maxSize) {
            slug = slug.substring(0, maxSize - randomHyphenSize);
        }

        this.slug = slug + "-" + randomString;
    }
}
