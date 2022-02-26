package com.example.medium_clone.application.user.repository;

import com.example.medium_clone.application.user.dto.ProfileGetDto;
import com.example.medium_clone.application.user.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileQueryRepository extends JpaRepository<Profile, Long> {

    @Query("select new com.example.medium_clone.application.user.dto.ProfileGetDto(p.id, p.bio, p.username) from Profile p where p.username = :username")
    Optional<ProfileGetDto> findProfileGetDtoByUsername(@Param("username") String username);

}
