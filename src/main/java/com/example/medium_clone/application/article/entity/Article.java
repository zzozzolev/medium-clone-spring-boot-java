package com.example.medium_clone.application.article.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import com.example.medium_clone.application.user.entity.Profile;
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

}
