package com.example.medium_clone.application.user.entity;

import com.example.medium_clone.application.common.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@ToString(of = {"id", "email"})
public class User extends BaseTimeEntity implements UserDetails {

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

    protected User() {}

    @Builder
    public User(String password, String email, Profile profile) {
        this.password = password;
        this.email = email;
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
