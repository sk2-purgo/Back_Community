package com.example.final_backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * application.properties에 정의된 JWT 설정을 바인딩
 * - JwtService에서 토큰 생성 시 필요한 필드를 해당 클래스를 통해 주입 받음
 */

@Configuration
@Getter
@Setter
public class JwtConfig {

    private final String secret;
    private final long expirationMs;
    private final long refreshExpirationMs;

    public JwtConfig(Dotenv dotenv) {
        this.secret = dotenv.get("JWT_SECRET");
        this.expirationMs = Long.parseLong(dotenv.get("JWT_EXPIRATION_MS", "3600000")); // 기본값: 1시간
        this.refreshExpirationMs = Long.parseLong(dotenv.get("JWT_REFRESH_EXPIRATION_MS", "604800000")); // 기본값: 7일
    }
}