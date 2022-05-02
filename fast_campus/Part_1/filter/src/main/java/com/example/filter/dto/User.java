package com.example.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체 생성자
public class User {
    private String name;
    private int age;

}