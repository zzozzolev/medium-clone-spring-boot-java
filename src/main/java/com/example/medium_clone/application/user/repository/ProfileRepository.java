package com.example.medium_clone.application.user.repository;


import com.example.medium_clone.application.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUsername(@Param("username") String username);
}
