package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired /* 이본적으로 인텔리제이가 생성자를 만들지 안겠니 */
    private final AsyncService asyncService;

    public ApiController(AsyncService asyncService){
        this.asyncService=asyncService;
    }

    @GetMapping("/hello")
    public CompletableFuture hello(){
        log.info(" API Controller hello method");
        return asyncService.run();
    }


    /*프록시 패턴이 사용됨 */
}
