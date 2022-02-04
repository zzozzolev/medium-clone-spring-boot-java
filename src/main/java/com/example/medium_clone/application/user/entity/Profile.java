package com.example.medium_clone.application.user.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@ToString(of = {"id", "username"})
public class Profile extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;
    private String bio;
    @Column(nullable = false)
    private String username;

    public static Profile createProfile(String bio, String username) {
        Profile profile = new Profile();
        profile.bio = bio;
        profile.username = username;

        return profile;
    }

    public void changeBio(String bio) {
        this.bio = bio;
    }

    public void changeUsername(String username) {
        this.username = username;
    }
}
