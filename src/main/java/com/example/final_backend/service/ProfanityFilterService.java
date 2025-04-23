package com.example.final_backend.service;

import com.example.final_backend.dto.TextDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class ProfanityFilterService {

    @Qualifier("purgoRestTemplate")
    private final RestTemplate purgoRestTemplate;

    @Value("${PURGO_CLIENT_API_KEY}")
    private String apiKey;

    @Value("${PURGO_PROXY_BASE_URL}")
    private String baseUrl;

    public String filter(String text) {
        TextDto dto = new TextDto();
        dto.setText(text);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey); // 👈 명시적 설정

        // 디버깅용 로그 (필요시 로그 라이브러리 사용 권장)
        System.out.println("[ProfanityFilterService] Sending to: " + baseUrl + "/api/filter");
        System.out.println("[ProfanityFilterService] Authorization: Bearer " + apiKey);
        System.out.println("[ProfanityFilterService] Payload: " + dto.getText());

        // 요청 전송
        HttpEntity<TextDto> requestEntity = new HttpEntity<>(dto, headers);
        return purgoRestTemplate.postForObject(baseUrl + "/api/filter", requestEntity, String.class);
    }
}
