     package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// Default transaction mode is read only.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserRegisterDto registerDto) {
        Profile profile = Profile.createProfile("", registerDto.getUsername());

        User user = User.builder()
                .password(registerDto.getPassword())
                .email(registerDto.getEmail())
                .profile(profile)
                .build();

        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
