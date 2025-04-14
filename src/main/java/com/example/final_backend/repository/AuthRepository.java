package com.example.final_backend.repository;

import com.example.final_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories(basePackages = "com.example.final_backend.repository")
public interface AuthRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);         // 사용자 이메일 조회
    Optional<UserEntity> findById(String id);               // 사용자 아이디 조회
    Optional<UserEntity> findByUsername(String username);   // 사용자 닉네임 조회
}