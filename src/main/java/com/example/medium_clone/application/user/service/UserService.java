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

        User user = User.createUser(
                    registerDto.getPassword(),
                    registerDto.getEmail(),
                    profile
                );

        userRepository.save(user);
        return user.getId();
    }
}
