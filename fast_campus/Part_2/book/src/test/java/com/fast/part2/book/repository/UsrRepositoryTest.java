package com.fast.part2.book.repository;

import com.fast.part2.book.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsrRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        userRepository.save((new User()));

        System.out.println(" >>>  " + userRepository.findAll());
    }
}
