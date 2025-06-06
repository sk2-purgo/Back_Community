package com.example.final_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 댓글 Entity
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    // 사용자 식별 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="userId")
    private UserEntity user;

    // 게시물 식별 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "postId")
    private PostEntity post;

    // 댓글 내용
    private String content;

    // 댓글 생성 일자
    private LocalDateTime createdAt;

    // 댓글 수정 일자
    private LocalDateTime updatedAt;

    // 댓글 작성 시 초기 설정
    public static CommentEntity create(UserEntity user, PostEntity post) {
        CommentEntity comment = new CommentEntity();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setContent("임시"); // placeholder
        return comment;
    }
}
