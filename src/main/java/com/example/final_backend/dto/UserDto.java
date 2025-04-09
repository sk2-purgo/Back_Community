package com.example.final_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String pw;
    private String profileImage;
}
