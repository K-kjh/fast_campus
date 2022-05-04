package com.example.restaurant.never.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix ="naver") //기본이 되는 뿌리 키값
@Data
@AllArgsConstructor
public class YamlConfiguration {
    private final List<Map<String,String>> cilent;
}
