![header](https://capsule-render.vercel.app/api?type=waving&color=gradient&height=192&section=header&text=Community%20Backend&fontSize=70&animation=fadeIn&fontColor=FFF)


<p align="center">
  <img src="https://img.shields.io/badge/redis-%23DC382D.svg?&style=for-the-badge&logo=redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/fastapi-%23009688.svg?&style=for-the-badge&logo=fastapi&logoColor=white"/>
  <img src="https://img.shields.io/badge/gradle-%2302303A.svg?&style=for-the-badge&logo=gradle&logoColor=white"/>
  <img src="https://img.shields.io/badge/java-%23007396.svg?&style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/docker-%232496ED.svg?&style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/mysql-%234479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white"/>
    <br>
  <img src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white"/>
  <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/>
  <img src="https://img.shields.io/badge/postman-%23FF6C37.svg?&style=for-the-badge&logo=postman&logoColor=white"/>
  <img src="https://img.shields.io/badge/spring%20security-%23007A33.svg?&style=for-the-badge&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/JWT-white?style=for-the-badge&logo=jsonwebtokens&logoColor=black"/>
</p>





## 프로젝트 소개

이 프로젝트는 비속어 사용을 줄이고 건강한 커뮤니티 문화를 조성하기 위한 PURGO 체험용 커뮤니티 백엔드 시스템입니다.  
AI 필터링 서버(PURGO_CORE)와 연동하여 사용자별 비속어 사용 횟수를 누적 관리합니다.  
누적 5회 이상 시, 게시글·댓글 작성 및 수정이 제한됩니다.  
이를 통해 PURGO를 활용한 자정 기능이 실제 서비스 환경에서 어떻게 작동하는지를 직접 체험할 수 있도록 설계되었습니다.

## Member
- 송보민(PL)   :  마이페이지, DB 연결(MySQL, Redis), JWT, 초기 설정 보수, ERD , 프록시 서버 구현, ai 연동
- 구강현       :  사용자 관리, JWT, 초기 설정, ERD, Query, 문서 작성 및 정리(Notion), ai 기능 연결(욕설 카운트, 기능 제한)
- 정혜지       :  게시글 CRUD, 검색, ERD, Query , swagger
- 이은비(보조)  :  댓글 CRUD, ERD

## 기본 설정
- application.properties 파일 생성
- 노션 -> 백엔드 설정/application.properties 참고
- java 17 
- Spring Boot 3.4.4
- gradle-8.13

## 로컬 Docker 설치
- Mysql
     - docker run --name mysql-db -e MYSQL_ROOT_PASSWORD={your_password} -p 3306:3306 -d mysql:8.4
          - 쿼리 : query.sql

- Redis
     - docker run -d --name redis -p 6379:6379 redis:7-alpine



## 프로젝트 기간
- 2025.03.26 ~ 2025.06.05



# 툴체인 & 프레임워크

## 프레임워크

| 분류               | 사용 기술                 | 설명                                            |
|--------------------|-----------------------|--------------------------------------------------|
| **백엔드 프레임워크** | Spring Boot           | REST API, 보안, DB 연동을 스타터 의존성 기반으로 통합 구성                    |
| **보안**            | Spring Security | 필터 체인 기반 인증/인가 흐름 구현, 세션리스 구조로 인증 처리             |
| **인증**            | JWT | 클라이언트 인증 상태를 유지하기 위한 토큰 기반 인증 방식           |
| **데이터베이스**     | MySQL                 | 관계형 데이터 정합성과 트랜잭션 처리에 특화된 구조                  |
| **데이터베이스**     | Redis                 | 인메모리 기반으로 고속 키-값 저장, TTL 설정, 구조 단순화에 최적화               |
| **ORM**            | JPA (Hibernate)       | 자바 객체로 DB를 직접 제어하고 유지보수성을 높이는 ORM 접근             |
| **이메일 발송**      | Spring Mail           | 인증 및 알림 메일 발송을 SMTP 설정 기반으로 간편하게 구현                                  |



## 툴체인


| 분류           | 사용 기술                | 설명                                                         |
|--------------|--------------------------|--------------------------------------------------------------|
| **IDE**      | IntelliJ IDEA            | Java 및 Spring Boot 개발에 최적화된 통합 개발 환경           |
| **빌드 도구**    | Gradle                   | 프로젝트 빌드 및 의존성 관리 자동화 도구                     |
| **버전 관리**    | Git + GitHub             | 소스 코드 이력 관리 및 협업 도구                             |
| **테스트 도구**   | Postman                  | REST API 테스트 및 문서화                                    |
| **기타 라이브러리** | Lombok                   | Getter, Setter, Builder 자동 생성                            |
| **JDK(JRE)** | Java 17                  | Spring 애플리케이션 런타임 환경                              |
| **DB 툴**     | DBeaver                  | MySQL RDS 접속 및 쿼리 테스트                                |
| **인프라 관리**   | AWS Console              | EC2, RDS, ElastiCache, S3 구성 및 모니터링 도구              |

---

## 백엔드 흐름도
<img src="image/백엔드%20흐름도.png"  width="700px">

----
## 인증 흐름
### [1] 로그인 시
```text
┌────────────┐
│클라이언트   │
└────┬───────┘
     ↓
POST /api/auth/login
     ↓
  ID/PW 인증
     ↓
  AccessToken + RefreshToken 생성
     ↓
토큰 응답 → 클라이언트 저장
```
### [2] 인증된 요청 시
```
┌────────────┐
│클라이언트   │
└────┬───────┘
     ↓
Authorization: Bearer <token>
     ↓
JwtAuthorizationFilter
  └→ 토큰 서명 검증 (라이브러리 내부)
  └→ userId(sub) 추출
  └→ DB에서 유저 조회
  └→ SecurityContext에 등록
     ↓
@AuthenticationPrincipal 접근 가능
```


---

# API
### Auth API


| 메서드 | 엔드포인트              | 설명                          |
|--------|--------------------------|-------------------------------|
| POST   | /auth/signup             | 회원가입                      |
| POST   | /auth/login              | 로그인 및 토큰 발급           |
| POST   | /auth/refresh            | 토큰 재발급                   |
| POST   | /auth/logout             | 로그아웃                      |
| GET    | /auth/checkId            | 아이디 중복 확인              |
| GET    | /auth/checkName          | 닉네임 중복 확인              |
| POST   | /auth/findId             | 이메일로 아이디 찾기          |
| POST   | /auth/resetPassword      | 비밀번호 재설정               |

---

### User API

| 메서드 | 엔드포인트                | 설명                            |
|--------|----------------------------|---------------------------------|
| GET    | /api/user/profile          | 내 프로필 조회                  |
| PUT    | /api/user/profile          | 내 프로필 수정                  |
| DELETE | /api/user/delete           | 회원 탈퇴                        |
| POST   | /api/user/penaltyCount     | 패널티 횟수 조회                 |
| POST   | /api/user/limits           | 사용자 제한 정보 조회           |

---

### Post API

| 메서드 | 엔드포인트                   | 설명                           |
|--------|-------------------------------|--------------------------------|
| GET    | /api/post/list                | 게시글 목록 조회 (페이지네이션) |
| GET    | /api/post/{postId}           | 게시글 상세 조회               |
| POST   | /api/post/create              | 게시글 작성                    |
| PUT    | /api/post/update/{postId}     | 게시글 수정                    |
| DELETE | /api/post/delete/{postId}     | 게시글 삭제                    |
| GET    | /api/post/my                  | 작성 게시글 조회             |


---

### Comment API

| 메서드 | 엔드포인트                   | 설명                           |
|--------|-------------------------------|--------------------------------|
| GET    | /api/comment/{postId}         | 특정 게시글의 댓글 조회        |
| POST   | /api/comment/{postId}         | 댓글 작성                      |
| PUT    | /api/comment/{commentId}      | 댓글 수정                      |
| DELETE | /api/comment/{commentId}      | 댓글 삭제                      |

---

### Search API

| 메서드 | 엔드포인트     | 설명                  |
|--------|----------------|-----------------------|
| GET    | /api/search    | 키워드로 게시글 검색 |

---

# 프로그램 구성
```
backend/
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── final_backend/
        │               ├── config/
        │               │   ├── JwtConfig.java
        │               │   ├── PurgoClientConfig.java
        │               │   ├── RedisConfig.java
        │               │   ├── SecurityConfig.java
        │               │   ├── SwaggerConfig.java
        │               │   └── WebMvcConfig.java
        │               ├── controller/
        │               │   ├── AuthController.java
        │               │   ├── CommentController.java
        │               │   ├── MypageController.java
        │               │   ├── PostController.java
        │               │   └── SearchController.java
        │               ├── dto/
        │               │   ├── AuthDto.java
        │               │   ├── CommentDto.java
        │               │   ├── JwtDto.java
        │               │   ├── PostDto.java
        │               │   └── ProfileDto.java
        │               ├── entity/
        │               │   ├── BadwordLogEntity.java
        │               │   ├── CommentEntity.java
        │               │   ├── PenaltyCountEntity.java
        │               │   ├── PostEntity.java
        │               │   ├── UserEntity.java
        │               │   └── UserLimitsEntity.java
        │               ├── factory/
        │               │   └── UserFactory.java
        │               ├── repository/
        │               │   ├── BadwordLogRepository.java
        │               │   ├── CommentRepository.java
        │               │   ├── PenaltyCountRepository.java
        │               │   ├── PostRepository.java
        │               │   ├── UserLimitsRepository.java
        │               │   └── UserRepository.java
        │               ├── security/
        │               │   ├── CustomUserDetails.java
        │               │   └── JwtAuthorizationFilter.java
        │               └── service/
        │                   ├── AsyncService.java
        │                   ├── AuthService.java
        │                   ├── CheckBadwordService.java
        │                   ├── CommentService.java
        │                   ├── JwtService.java
        │                   ├── MypageService.java
        │                   ├── PostService.java
        │                   ├── RedisService.java
        │                   ├── ServerToProxyJwtService.java
        │                   ├── UserDetailsServiceImpl.java
        │                   └── UserPenaltyService.java
        └── resources/
            ├── application.properties
            └── dummy.txt
```
##  욕설 필터링 방식

- `BadwordFilterService`는 FastAPI 서버에 메시지를 POST 요청으로 전달
- FastAPI 응답 구조 예시:
```json
{
  "isAbusive": true,
  "originalText": "욕설 포함된 문장",
  "rewrittenText": "***"
}
```
- 욕설이 감지되면 `chatService.incrementBadwordCount()` 실행
- FastAPI 장애 시 원문 그대로 반환

---
## 핵심 기능 요약

- **게시글 작성, 수정, 삭제, 조회**  
  로그인한 사용자는 게시글을 작성하고, 본인이 작성한 글은 수정·삭제할 수 있습니다.

- **댓글 작성, 수정, 삭제**  
  게시글에 댓글을 달 수 있으며, 작성자는 댓글 수정과 삭제가 가능합니다.

- **욕설 필터링 (FastAPI 연동)**  
  게시글과 댓글은 AI 서버와 연동되어 욕설을 실시간 감지하고 자동으로 대체어로 변환합니다.

- **JPA 기반 DB 연동**  
  JPA를 통해 객체 중심으로 DB에 접근하고 데이터를 자동 매핑 및 저장합니다.

- **비속어 사용 횟수 누적 관리**  
  감지된 욕설은 사용자별로 누적 기록되며, 로그로 저장됩니다.

- **누적 시 제한 기능**  
  비속어 5회 이상 사용 시, 게시글·댓글 작성 및 수정이 자동으로 제한됩니다.

- **JWT 인증 및 토큰 갱신**  
  JWT를 통해 사용자 인증을 처리하며, Access Token이 만료되면 Refresh Token을 이용해 API 요청을 통해 갱신할 수 있습니다.

