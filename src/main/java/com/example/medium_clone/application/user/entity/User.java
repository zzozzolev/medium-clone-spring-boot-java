package com.example.medium_clone.application.user.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(of = {"id", "email"})
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public static User createUser(String password, String email, Profile profile) {
        User user = new User();
        user.password = password;
        user.email = email;
        user.profile = profile;

        return user;
    }
}
