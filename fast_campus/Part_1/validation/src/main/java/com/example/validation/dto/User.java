package com.example.validation.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class User {
    //모든 validation 어노테이션 뒤에 message 를 넣을수 있다.
    
    @NotBlank(message ="이름에 공백이 생겼어요. 주륵")
    private String name;

    @Min(value=0)
    private int age;

    /* email 형식 */
    @Email
    private String email;

    // 전화번호 정규식 패턴
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message ="핸드폰 번호의 양식과 맞지 않습니다. 01x-xxxx-xxxx")
    private String phoneNumber;

}
