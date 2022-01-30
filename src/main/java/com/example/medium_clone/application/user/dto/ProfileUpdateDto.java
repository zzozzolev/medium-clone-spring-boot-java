package com.example.medium_clone.application.user.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class ProfileUpdateDto {

    private String username;
    private String bio;

    // Getter 반환 타입을 Optional로 표시해, request body에 명시되지 않을 수도 있는 것을 나타낸다.

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    public Optional<String> getBio() {
        return Optional.ofNullable(bio);
    }
}
