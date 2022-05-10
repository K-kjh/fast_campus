package com.fast.part2.book.repository;

import com.fast.part2.book.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author K-kjh
 * @since 2022/05/07
 */
public interface UserRepository extends JpaRepository<User,Long> {

   /*
   * after : 크다
   * before :작다
   * greaterThen : 보다큰
   * ~Between~     : ~와 ~사이
   * and : 그리고
   * or  : 또는
   * ~By : ~에 클래스
   * NotNull : 널이 아닌것
   * empty : "" 이 아닌것 //객체에서 empty 컬렉션의 empty
   * is:  맞나아닌가.
   *
    */
   Set<User> findByName(String name);
   User findByEmail(String email);
   Object findUserByName(String name);

   User getByEmail(String email);
   User readByEmail(String email);
   User queryByEmail(String email);
   User searchByEmail(String email);
   User streamByEmail(String email);

   List<User> findFirst1ByName(String name);
   List<User> findTop2ByName(String name);

   List<User> findByCreatedAtAfter(LocalDateTime dateTime); //특정 날짜보다 큰것 before 은 반대이기때문에 작은것

   User findByCreatedAtGreaterThan(LocalDateTime dateTime);

   List<User> findByCreatedAtBetween(LocalDateTime dateTime,LocalDateTime dateTime2);
   List<User> findByIdBetween(Long a,Long b);

   List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1,Long id2);

   List<User> findByIdIsNotNull();

   List<User> findByNameIn(List<String> names);
   List<User> findByNameStartingWith(String name);
   List<User> findByNameEndingWith(String name);
   List<User> findByNameContains(String name);
   List<User> findByNameLike(String name);
   List<User> findTop1ByName(String name);

   List<User> findTopByNameOrderByIdAsc(String name);
   List<User> findFirstByNameOrderByIdDescEmailAsc(String name);  // 아이디는 역순  이메일 정순으로 정렬
   List<User> findFirstByName(String name, Sort sort);
}
