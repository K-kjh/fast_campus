package com.example.validation.dto;


import com.example.validation.annotation.YearMonth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class User {
    //모든 validation 어노테이션 뒤에 message 를 넣을수 있다.
    
    @NotBlank(message ="이름에 공백이 생겼어요. 주륵")
    private String name;

    @Min(value=0)
    private int age;

    /* email 형식 */
    @Email
    private String email;

    /* 전화번호 정규식 패턴*/
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message ="핸드폰 번호의 양식과 맞지 않습니다. 01x-xxxx-xxxx")
    private String phoneNumber;

    //?? @Size(min=6,max=6)
    @YearMonth
    /* 직접 설계 흐름 : anonotation.YearMonth -> @Constraint(YearMonthValidator.class)
     *  YearMonthValidator 클래스에서 오버라이딩한 ConstraintValidator 의 메소드 isValid 으로 패턴 확인
     */
    private String reqYearMonth;

    @Valid
    /* 특정 클래스에 검사를 하고 싶으면 valid를 꼭 붙여라
     */
    private List<Car> cars;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}
