     package com.example.medium_clone.application.user.service;

import com.example.medium_clone.application.user.dto.UserRegisterDto;
import com.example.medium_clone.application.user.entity.Profile;
import com.example.medium_clone.application.user.entity.User;
import com.example.medium_clone.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

     @Service
// Default transaction mode is read only.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long join(UserRegisterDto registerDto) {
        User user = createUser(registerDto);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    /**
     * Get User instance using registerDto.
     * @return User instance
     */
    protected User createUser(UserRegisterDto registerDto) {
        Objects.requireNonNull(registerDto, "registerDto");

        Profile profile = Profile.builder()
                .bio("")
                .username(registerDto.getUsername())
                .build();

        return User.builder()
                .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
                .email(registerDto.getEmail())
                .profile(profile)
                .build();
    }
}
