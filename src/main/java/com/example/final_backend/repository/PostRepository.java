package com.example.final_backend.repository;

import com.example.final_backend.entity.PostEntity;
import com.example.final_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByUserId(UserEntity userId);
    List<PostEntity> findAllByOrderByCreatedAtDesc();
    // 제목으로 검색
    List<PostEntity> findByTitleContaining(String title);

    // 내용으로 검색
    List<PostEntity> findByContentContaining(String content);

    // 제목 또는 내용으로 검색
    @Query("SELECT p FROM PostEntity p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<PostEntity> findByTitleOrContentContaining(@Param("keyword") String keyword);

    // 특정 사용자의 게시물 목록
    List<PostEntity> findByUserId_Id(String userId);

    Optional<Object> findByPostId(int postId);
}
