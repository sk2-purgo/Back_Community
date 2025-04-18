# Backend_Purgo

## 프로젝트 소개

## Member
- 송보민(PL)   :  마이페이지, DB 연결(MySQL, Redis), JWT, 초기 설정 보수, ERD
- 구강현       :  사용자 관리, JWT, 초기 설정, ERD, Query, 문서 작성 및 정리(Notion)
- 정혜지       :  게시글 CRUD, 검색, ERD, Query
- 이은비(보조)  :  댓글 CRUD, ERD

## 기본 설정
- application.properties 파일 생성
- 노션 -> 백엔드 설정/application.properties 참고

## 로컬 Docker 설치
- docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 -d mysql:8.4
- docker run -d --name redis -p 6379:6379 redis:7-alpine

## 프로젝트 기간
- 2025.03.26 ~ 2025.06.04

## 프로그램 구성
### Config
- JwtConfig.java
- RedisConfig.java
- SecurityConfig.java

### Controller
- AuthController.java
- UserController.java
- PostController.java
- CommentController.java

### Dto
- AuthDto
- JwtDto
- PostDto
- CommentDto

### Entity
- BadwordLogEntity.java
- CommentEntity.java
- PenaltyCountEntity.java
- PostEntity.java
- UserEntity.java
- UserLimitsEntity.java

### Repository
- AuthRepository.java
- PostRepository.java
- CommentRepository.java

### Security
- CustomUserDetails.java
- JwtAuthorizationFilter.java

### Service
- AuthService.java
- JwtService.java
- RedisService.java
- UserDetailsServiceImpl.java
- UserService.java
- PostService.java
- CommentService.java


---

## API
### 🔐 Auth API

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

### 👤 User API

| 메서드 | 엔드포인트                | 설명                            |
|--------|----------------------------|---------------------------------|
| GET    | /api/user/profile          | 내 프로필 조회                  |
| PUT    | /api/user/profile          | 내 프로필 수정                  |
| POST   | /api/user/profile/upload   | 프로필 이미지 업로드            |
| DELETE | /api/user/delete           | 회원 탈퇴                        |
| POST   | /api/user/penaltyCount     | 패널티 횟수 조회                 |
| POST   | /api/user/limits           | 사용자 제한 정보 조회           |

---

### 📝 Post API

| 메서드 | 엔드포인트                   | 설명                           |
|--------|-------------------------------|--------------------------------|
| GET    | /api/post/list                | 게시글 목록 조회 (페이지네이션) |
| GET    | /api/post/{postId}           | 게시글 상세 조회               |
| POST   | /api/post/create              | 게시글 작성                    |
| PUT    | /api/post/update/{postId}     | 게시글 수정                    |
| DELETE | /api/post/delete/{postId}     | 게시글 삭제                    |
| GET    | /api/post/my                  | 내가 작성한 게시글 조회        |

---

### 💬 Comment API

| 메서드 | 엔드포인트                   | 설명                           |
|--------|-------------------------------|--------------------------------|
| GET    | /api/comment/{postId}         | 특정 게시글의 댓글 조회        |
| POST   | /api/comment/{postId}         | 댓글 작성                      |
| PUT    | /api/comment/{commentId}      | 댓글 수정                      |
| DELETE | /api/comment/{commentId}      | 댓글 삭제                      |

---

### 🔍 Search API

| 메서드 | 엔드포인트     | 설명                  |
|--------|----------------|-----------------------|
| GET    | /api/search    | 키워드로 게시글 검색 |