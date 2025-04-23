package com.example.final_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PurgoClientConfig {

    @Value("${purgo.proxy.base-url}")
    private String baseUrl;

    @Value("${purgo.proxy.api-key}")
    private String apiKey;

    /** purgo‑proxy 호출 전용 RestTemplate (Authorization 자동 첨가) */
    @Bean(name = "purgoRestTemplate")
    public RestTemplate purgoRestTemplate(RestTemplateBuilder builder) {

        ClientHttpRequestInterceptor authInterceptor = (req, body, ex) -> {
            req.getHeaders().set("Authorization", "Bearer " + apiKey);
            return ex.execute(req, body);
        };

        return builder
                .rootUri(baseUrl)                       // <─ base URL 주입
                .additionalInterceptors(authInterceptor)
                .build();
    }
}
