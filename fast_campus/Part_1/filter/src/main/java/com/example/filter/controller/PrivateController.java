package com.example.filter.controller;

import com.example.filter.annotation.Auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
@Auth
public class PrivateController {

    @GetMapping("/hello")
    @Auth // 클래스 메소드 둘다 가능 하자민 메소드는 비추
    public String hello(){
        return "private hello";
    }
}
