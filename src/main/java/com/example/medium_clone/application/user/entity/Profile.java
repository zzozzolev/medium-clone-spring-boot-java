package com.example.medium_clone.application.user.entity;

import com.example.medium_clone.application.article.entity.Article;
import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(of = {"id", "username"})
public class Profile extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Article> articles = new ArrayList<>();

    private String bio;
    @Column(nullable = false)
    private String username;

    protected Profile () {}

    @Builder
    public Profile(String bio, String username) {
        this.bio = bio;
        this.username = username;
    }

    public void changeBio(String bio) {
        this.bio = bio;
    }

    public void changeUsername(String username) {
        this.username = username;
    }
}
