package com.example.medium_clone.application.user.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString(of = {"id", "email"})
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String password;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
