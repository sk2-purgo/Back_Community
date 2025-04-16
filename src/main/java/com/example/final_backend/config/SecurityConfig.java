package com.example.final_backend.config;

import com.example.final_backend.security.JwtAuthorizationFilter;
import com.example.final_backend.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Spring Security 설정
 * - JWT 필터 등록(JwtAuthorizationFilter)
 * - 인증 예외 처리
 * - 공개 URL 설정 /auth/** 인증 없이 접근 가능
 * - CORS 설정(localhost:3000)
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 매니저 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // 로그인 인증 처리에 사용(AuthService에서 사용)
        return authConfig.getAuthenticationManager();
    }

    // 보안 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1) CORS 설정 추가
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 사용자 관리 관련 인증 경로
                        .requestMatchers("/api/auth/**").permitAll()

                        // 검색 관련 인증 경로
                        .requestMatchers(HttpMethod.GET, "/api/search").permitAll()

                        // 게시글 관련 인증 경로
                        .requestMatchers(HttpMethod.GET, "/api/post/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/post/create").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/post/update/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/post/delete/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/post/my").authenticated()

                        // 유저 관련 인증 경로
                        .requestMatchers("/user/**").authenticated()

                        // 댓글 관련 인증 경로
                        .requestMatchers("/comment/**").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("🔒 접근 거부됨! 이유: " + accessDeniedException.getMessage());
                            response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        })
                )
                // 2) JWT 필터 추가(기존 코드와 동일)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    // CORS 세부 설정(예: 로컬 프론트 3000 허용)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // 프론트 주소
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Refresh-Token");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}