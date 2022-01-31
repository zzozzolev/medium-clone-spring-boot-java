package com.example.medium_clone.application.article.entity;

import com.example.medium_clone.application.common.util.RandomStringGenerator;
import com.github.slugify.Slugify;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private static final Slugify slugify = new Slugify();
    private static final RandomStringGenerator generator = new RandomStringGenerator();
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

        // when
        String slug = Article.getSlugWithTitle(slugify, generator, title, size, maxSize);

        // then
        assertThat(slug.length()).isEqualTo(maxSize);
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

        // when
        String slug = Article.getSlugWithTitle(slugify, generator, title, size, maxSize);

        // then
        assertThat(slug.length()).isEqualTo(maxSize);
    }

}