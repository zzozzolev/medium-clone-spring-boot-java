package com.example.medium_clone.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileGetDto {

    private Long id;
    private String bio;
    private String username;

}
