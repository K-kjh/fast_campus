package com.example.filter.config;

import com.example.filter.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor //생성자에서 주입
public class MvcConfig  implements WebMvcConfigurer {


    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/api/private/*");/* 특정 url 을 뺴고 싶다.*/

    }
}
