package com.example.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** validation
 *  단편 설명 : 정상 로직을 구현하기 전에 검증해야할 코드를 길이가 길어질 경우
 *				이를 해결하고자 미리 검증을 하는 과정을 validation 이라고 한다.
 */
@SpringBootApplication
public class ValidationApplication {


	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);
	}

}
