package com.example.medium_clone.application.article.entity;

import com.example.medium_clone.application.user.entity.Profile;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    private static final Slugify slugify = new Slugify();
    private static final int size = 12;
    private static final int maxSize = 300;

    /*
    slug.length() + randomHyphenSize == maxSize
    substring 처리 안 하는 경우
     */
    @Test
    public void testSetSlugWithTitleEqualLength() {
        // given
        // hyphen 길이가 더해져서 1을 추가로 빼줘야한다.
        String title = "a".repeat(maxSize - size - 1);
        Article article = new Article();

        // when
        article.setSlug(slugify, title, size, maxSize);

        // then
        assertThat(article.getSlug().length()).isEqualTo(maxSize);
    }

    /*
    slug.length() + randomHyphenSize == maxSize + 1
    substring 처리하는 경우
     */
    @Test
    public void testSetSlugWithTitleGreaterLength() {
        // given
        // hyphen 길이가 더해져서 1을 추가로 빼주지 않아도 된다.
        String title = "a".repeat(maxSize - size);
        Article article = new Article();

        // when
        article.setSlug(slugify, title, size, maxSize);

        // then
        assertThat(article.getSlug().length()).isEqualTo(maxSize);
    }

    @Test
    public void testSetAuthorNonNull() {
        // given
        Profile profile = null;
        Article article = new Article();

        // when, then
        Assertions.assertThrows(NullPointerException.class, () -> article.setAuthor(profile));
    }

}