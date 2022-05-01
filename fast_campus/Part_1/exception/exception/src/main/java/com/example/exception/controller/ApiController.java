package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("")
    public User get(@RequestParam(required = false)/* 필수는 아님*/ String name,@RequestParam (required = false)Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        int a= 10+ age;
        System.out.println("name = " + name + ", age = " + age);
        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println("user = " + user);
        return user;
    }
}
