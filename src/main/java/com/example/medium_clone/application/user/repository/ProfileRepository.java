package com.example.medium_clone.application.user.repository;


import com.example.medium_clone.application.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
