//package com.example.final_backend.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name="limits")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class UserLimitsEntity {
//    // 이용자 제한 식별 번호 -> 이거 필요한가?
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int limitId;
//
//    // 제한된 사용자
//    @OneToOne
//    @JoinColumn(name = "users")
//    private UserEntity userId;
//
//    // 제한 시작 일자
//    private LocalDateTime startDate;
//
//    // 제한 끝나는 일자
//    private LocalDateTime endDate;
//
//    // 제한 여부
//    private Boolean isActive;
//}
