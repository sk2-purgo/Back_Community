package com.example.final_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="logs")
@Data
public class BadwordLogEntity {
    // 비속어 사용 로그 식별 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LogId;

    // 사용자 식별 번호
    @ManyToOne
    @JoinColumn(name = "users")
    private UserEntity userId;

    // 게시물 식별 번호
    @OneToOne
    @JoinColumn(name = "posts")
    private PostEntity postId;

    // 댓글 식별 번호
    @OneToOne
    @JoinColumn(name = "comments")
    private CommentEntity commentId;

    // 사용한 비속어
    private String detectedWord;

    // 비속어 사용 일자
    private LocalDateTime detectedAt;
}
