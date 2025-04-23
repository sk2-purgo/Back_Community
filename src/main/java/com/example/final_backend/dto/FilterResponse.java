package com.example.final_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class FilterResponse {
    private String rewritten_text;
    private String original_text;
    private List<BadWord> fasttext_bad_words;

    @JsonProperty("is_abusive")
    private boolean abusive;  // ✅ 이름은 자유롭게 설정 가능

    private double kobert_confidence;
    private int kobert_pred;

    @Data
    public static class BadWord {
        private String word;
        private double prob;
    }
}
