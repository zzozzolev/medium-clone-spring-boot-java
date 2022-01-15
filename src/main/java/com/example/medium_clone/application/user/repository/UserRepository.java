package com.example.medium_clone.application.user.repository;

import com.example.medium_clone.application.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
