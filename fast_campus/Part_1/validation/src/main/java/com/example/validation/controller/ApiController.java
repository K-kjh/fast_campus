package com.example.validation.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    //BindingResult 는 에러메세지가 담겨있다.
    //@Valid 을 붙이면 user 클래스에서 사용되는 validation 을 사용할수 있다.
    @PostMapping("/user")
    public ResponseEntity user (@Valid @RequestBody User user, BindingResult bindingResult){
        System.out.println("user = " + user);

        if( bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field = " + field.getField());
                System.out.println("message = " + message);

                sb.append("field: "+ field.getField());
                sb.append("message:"+message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        return ResponseEntity.ok(user);
    }
}
