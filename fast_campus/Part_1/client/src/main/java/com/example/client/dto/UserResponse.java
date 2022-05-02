package com.example.client.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String Name;
    private int age;

    @Override
    public String toString() {
        return "UserResponse{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }
}
