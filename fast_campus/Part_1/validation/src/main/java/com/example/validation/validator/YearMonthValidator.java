package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//어노테이션 활용 클래스
public class YearMonthValidator implements ConstraintValidator<YearMonth,String> {

    //확인 해야할값
    private String pattern;

    //초기화 시킬떄 어노테이션의 패턴 확인
    @Override
    public void initialize(YearMonth constraintAnnotation) {
      this.pattern = constraintAnnotation.pattern();
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // yyyyMM
        //dd 를 붙이는 이유는 localDate 검사할떄 dd가 필요하기 때문이다.
        try {
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
