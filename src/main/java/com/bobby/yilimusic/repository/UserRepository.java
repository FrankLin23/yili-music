package com.bobby.yilimusic.repository;

import com.bobby.yilimusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getByUsername(String username);
}
