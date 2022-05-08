package com.fast.part2.book.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * @author K-kjh
 * @since 2022/05/07
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User { //  레파지토리 있어야 조회등 가능
    @Id //pk
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
