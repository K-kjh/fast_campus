package com.fast.part2.book.repository;

import com.fast.part2.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author K-kjh
 * @since 2022/05/07
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
