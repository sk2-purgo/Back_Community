package com.example.final_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TextDto {
    @NotBlank
    private String text;
}
