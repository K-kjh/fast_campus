package com.example.exception.advice;

import com.example.exception.dto.Error;
import com.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/** 전체 적인 Exception 을 잡기
 *
    @RestControllerAdvice(basePackges =" com.example") -> 이 하위 패키지 예외코들를 잡을거야
 *
 * 특정 controller 안에
 *     @ExceptionHandler(value = Met hodArgumentNotValidException.class)
 *     을 넣어줄수 있다. (중복 가능)
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    //잡고자하는 인셉션
    //서버에서 일언나는 모든 인셉션
    @ExceptionHandler(value =Exception.class)
    public ResponseEntity exception(Exception e){
        e.printStackTrace();

      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hello");
    }

    //특정 메소드의 에러코드를 잡고 싶다면
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){

        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();

        //e 의 반환값 getBindingReuslt을 가져온다.
        //bindingReuglt.getAllError 을 이용해서 error 에 넣어준다.
        //fieldError 형변환으로 error 을 가져온다.
        bindingResult.getAllErrors().forEach( error ->{
            FieldError field = ( FieldError) error;
            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            System.out.println(fieldName);
            System.out.println(message);
            System.out.println(value);
            //--------------------------

            Error errorMessage = new Error();
            errorMessage.setField(fieldName);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(value);

            errorList.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");


        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e){

            e.getConstraintViolations().forEach(error ->{
                Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(),false);
                List<Path.Node> list = stream.collect(Collectors.toList());


                String field =  list.get(list.size()-1).getName();
                String message = error.getMessage();
                String inv = error.getInvalidValue().toString();

                System.out.println("field = " + field);
                System.out.println("message = " + message);
                System.out.println("inv = " + inv);

            });
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity methodMissingServletRequestParameterException(MissingServletRequestParameterException e,HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String filedType = e.getParameterType();
        String invalidValue = e.getMessage();

        System.out.println("fieldName = " + fieldName);
        System.out.println("filedType = " + filedType);
        System.out.println("invalidValue = " + invalidValue);


        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(e.getMessage());
        errorMessage.setInvalidValue(invalidValue);

        errorList.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
