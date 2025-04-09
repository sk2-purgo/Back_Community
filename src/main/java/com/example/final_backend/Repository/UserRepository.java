package com.example.final_backend.Repository;

import com.example.final_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
//    Optional<UserEntity> findById(String id);  // 사용자 ID로 조회
}
