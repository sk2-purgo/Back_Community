//package com.example.final_backend.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserEntity {
//    // 사용자 식별 번호
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int userId;
//
//    //  사용자 ID
//    @Column(length = 20)
//    private String id;
//
//    // 사용자 닉네임
//    @Column(nullable = false, length = 10)
//    private String userName;
//
//    // 사용자 인증 이메일
//    @Column(nullable = false, length = 40)
//    private String email;
//
//    // 사용자 비밀번호
//    @Column(nullable = false)
//    private String password;
//
//    // 로그인 타입 (ex.kakao, 일반) -> 질문 필요
//    @Column(length = 10)
//    private String loginType;
//
//    // 프로필 이미지
//    @Column()
//    private String profileImage;
//
//    // 회원 가입 일자
//    private LocalDateTime createdAt;
//
//    // 개인정보 수정 일자
//    private LocalDateTime updatedAt;
//
//
//
//    // 게시물과 1대다 양방향 연결
//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    private List<PostEntity> post;
//
//    // 댓글과 1대다 양방향 연결
//    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    private List<CommentEntity> comment;
//
//    // 패널티 횟수 1대1 양방향 연결
//    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
//    private PenaltyCountEntity penaltyCount;
//}
