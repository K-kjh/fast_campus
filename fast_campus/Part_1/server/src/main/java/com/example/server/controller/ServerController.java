package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName("steve");
        user.setAge(10);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public  Req<User> post(
                    /*HttpEntity<String> entity */
                    @RequestBody  Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization")  String authorization,
                     @RequestHeader("custom-header") String customHeader
                     ){
       // System.out.println("user = " + user + ", userId = " + userId + ", userName = " + userName + ", authorization = " + authorization + ", customHeader = " + customHeader);

        Req<User> response = new Req<>();
        response.setHeader(
            new Req.Header()
        );

        response.setBody(
          response.getBody()
        );

        System.out.println("user = " + user + ", userId = " + userId + ", userName = " + userName + ", authorization = " + authorization + ", customHeader = " + customHeader);
        return user;
    }
}
