package com.example.client.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
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
