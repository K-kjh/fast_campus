package com.fast.part2.book.repository;

import com.fast.part2.book.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
public class UsrRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        userRepository.save((new User()));

        for(int i=2;i<10;i++) {
            User use1 = new User().builder()
                    .name("kang"+i)
                    .email("kgh2252_"+i+"@gmail.com")
                    .createdAt(now())
                    .updatedAt(now())
                    .build();

            userRepository.save(use1);
        }

        System.out.println(" >>>  " + userRepository.findAll());
        userRepository.findAll().forEach(System.out::println);

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        users.forEach(System.out::println);

        users = userRepository.findAllById(Lists.newArrayList(1L)); //아규먼트에 포함된 가져오기 T 객체도 가능
        users.forEach(System.out::println);

        User susers  = userRepository.findById(1L).orElse(null);
        System.out.println("susers = " + susers);

        userRepository.saveAndFlush(new User(null,"new kang","newkang@naver.com",now(),now()));
        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);

        Long count = userRepository.count(); //카운트
        boolean exists = userRepository.existsById(1L); // 있나없나
        //---------------------------------Delete
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.deleteById(2L);
//        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L,3L)));
//        userRepository.findAll().forEach(System.out::println);
        //-------------------------------페이징

        Page userspage = userRepository.findAll(PageRequest.of(0,3));

        System.out.println("page = " + userspage);
        System.out.println("Elements = " + userspage.getTotalElements());           //전체 엘리멘트의 갯수
        System.out.println("totalPages = " + userspage.getTotalPages());            //전체 페이지
        System.out.println("NumberOfElements = " + userspage.getNumberOfElements());//현제 가져온 레코드수가 몇개인지
        System.out.println("sort = " + userspage.getSort());                        // sort 출력
        System.out.println("size = " + userspage.getSize());                        // 사이즈
        System.out.println("contents = " + userspage.getContent());                 // 내용

        //---------------------------- query by Example
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")                                            //매칭하지 않는다 .
                .withMatcher("email",endsWith());                        // 확인 하겟다.

        //가자 entity
        // 생각처엄 Example 은 많이 쓰이지 않는단다.
        Example<User> example = Example.of(new User("kang","com"),matcher);

        userRepository.findAll(example).forEach(System.out::println);


    }
}
