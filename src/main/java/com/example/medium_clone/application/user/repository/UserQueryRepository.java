package com.example.medium_clone.application.user.repository;

import com.example.medium_clone.application.user.dto.UserRegisterResponseDto;
import com.example.medium_clone.application.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    @Query("select new com.example.medium_clone.application.user.dto.UserRegisterResponseDto(p.username, u.email) from User u join u.profile p where u.id = :user_id")
    Optional<UserRegisterResponseDto> findUserRegisterResponseDtoById(@Param("user_id") Long id);

}
