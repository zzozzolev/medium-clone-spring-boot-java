package com.example.medium_clone.application.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    // TODO: Null 허용 안 되도록하기
    @Email
    private String email;
}
