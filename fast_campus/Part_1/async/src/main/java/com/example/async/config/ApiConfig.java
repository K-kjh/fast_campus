package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ApiConfig {

    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async");

        //코어풀 사이즈 10개
        // 10개를 다쓰면 큐에 내용이 다들어감
        // 예시 10 -> 20 -> 30 ->.... 100
        // setCorePoolSize : 기본 사이즈
        // setQueueCapacity : 추가되는 사이즈
        // setMaxPoolSize : 최대 사이즈
            return threadPoolTaskExecutor;
    }
}
