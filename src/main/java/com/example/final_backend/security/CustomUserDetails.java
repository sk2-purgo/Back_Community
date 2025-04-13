package com.example.final_backend.security;

import com.example.final_backend.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    public String getId() {
        return user.getId();  // 로그인 식별자
    }

    @Override
    public String getUsername() {
        return user.getId();  // ID가 username 역할
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;  // 권한 없으면 null 또는 빈 리스트
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
