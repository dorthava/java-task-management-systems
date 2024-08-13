package com.effective.mobile.tasks.repository;

import com.effective.mobile.tasks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
}

