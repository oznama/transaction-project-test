package com.soaint.ms_auth.repository;

import com.soaint.ms_auth.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
    Optional<UserAuth> findByUsername(String username);
}
