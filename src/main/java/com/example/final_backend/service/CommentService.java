package com.example.final_backend.service;

import com.example.final_backend.dto.CommentDto;
import com.example.final_backend.entity.CommentEntity;
import com.example.final_backend.entity.PostEntity;
import com.example.final_backend.entity.UserEntity;
import com.example.final_backend.repository.AuthRepository;
import com.example.final_backend.repository.CommentRepository;
import com.example.final_backend.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthRepository authRepository;

    private final String proxyApiUrl = "http://localhost:8001/proxy/analyze"; // ✅ FastAPI 중계 서버 주소

    // ✅ FastAPI 중계서버 호출 메서드
    private String refineIfNeeded(String text) {
        try {
            System.out.println("📤 FastAPI로 전송할 텍스트: " + text);
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> body = new HashMap<>();
            body.put("text", text);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(proxyApiUrl, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> result = response.getBody();
                boolean isAbusive = (boolean) result.get("is_abusive");

                if (isAbusive) {
                    String rewritten = (String) result.get("rewritten_text");
                    System.out.println("🛑 욕설 감지됨 → 정제된 문장으로 대체됨");
                    return rewritten;
                }
            } else {
                System.out.println("⚠️ FastAPI 응답 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("❌ FastAPI 요청 실패: " + e.getMessage());
        }
        return text;
    }

    // ✅ 게시글에 달린 댓글 목록 조회
    public List<CommentDto.CommentResponse> getCommentsByPostId(int postId) {
        PostEntity post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<CommentEntity> comments = commentRepository.findByPost(post);
        List<CommentDto.CommentResponse> commentResponse = new ArrayList<>();

        for (CommentEntity comment : comments) {
            CommentDto.CommentResponse dto = new CommentDto.CommentResponse();
            dto.setCommentId(comment.getCommentId());
            dto.setUserId(comment.getUser().getId());
            dto.setUsername(comment.getUser().getUsername());
            dto.setContent(comment.getContent());
            dto.setCreatedAt(comment.getCreatedAt());
            dto.setUpdatedAt(comment.getUpdatedAt());
            commentResponse.add(dto);
        }
        return commentResponse;
    }

    // ✅ 댓글 작성
    @Transactional
    public void createComment(String userId, int postId, CommentDto.CommentRequest commentRequest) {
        String original = commentRequest.getContent();
        String refined = refineIfNeeded(original); // ✅ 정제된 문장 받아오기
        commentRequest.setContent(refined);        // ✅ 덮어쓰기

        UserEntity user = authRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        CommentEntity comment = new CommentEntity();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    // ✅ 댓글 수정
    @Transactional
    public void updateComment(String userId, int commentId, CommentDto.CommentRequest commentRequest) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("댓글 수정 권한이 없습니다.");
        }

        comment.setContent(commentRequest.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    // ✅ 댓글 삭제
    @Transactional
    public void deleteComment(String userId, int commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
