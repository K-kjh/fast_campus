package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 전체 적인 Exception 을 잡기
 *
    @RestControllerAdvice(basePackges =" com.example") -> 이 하위 패키지 예외코들를 잡을거야
 *
 * 특정 controller 안에
 *     @ExceptionHandler(value = MethodArgumentNotValidException.class)
 *     을 넣어줄수 있다. (중복 가능)
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    //잡고자하는 인셉션
    //서버에서 일언나는 모든 인셉션
    @ExceptionHandler(value =Exception.class)
    public ResponseEntity exception(Exception e){
       // e.printStackTrace();
        System.out.println("e = " + e);
        System.out.println(e.getClass().getName());

      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hello");
    }

    //특정 메소드의 에러코드를 잡고 싶다면
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){


        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
