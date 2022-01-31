package com.example.medium_clone.application.article.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import com.example.medium_clone.application.common.util.RandomStringGenerator;
import com.example.medium_clone.application.user.entity.Profile;
import com.github.slugify.Slugify;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(of = {"id", "slug"})
public class Article extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile author;

    private String title;
    private String slug;
    private String body;
    private String description;

    public void setSlugWithTitle(Slugify slugify, RandomStringGenerator generator, String title, int size, int maxSize) {
        String slug = slugify.slugify(title);
        String randomString = generator.getRandomString(size);

        int randomHyphenSize = randomString.length() + 1;
        // slug 길이와 랜덤 스트링 길이의 합이 최대 길이보다 큰 경우
        if (slug.length() + randomHyphenSize > maxSize) {
            slug = slug.substring(0, maxSize - randomHyphenSize);
        }

        this.slug = slug + "-" + randomString;
    }
}
