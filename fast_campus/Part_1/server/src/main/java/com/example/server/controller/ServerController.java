package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerController {


    @GetMapping("/never")
    public  String never(){

        String query ="갈비집";
        String encode = Base64.getEncoder()
                .encodeToString(query.getBytes(StandardCharsets.UTF_8));


        String serch=encode;

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query",serch)
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")

                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req =  RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","Xed0ceZCNmsLqUzazW7w")
                .header("X-Naver-Client-Secret","NL38DjQsVz")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);
      //  log.info(result.toString());
        return result.toString();

    }
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
