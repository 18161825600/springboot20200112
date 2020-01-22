package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String userName;

    private String password;

    private String nickName;

    private String phoneNum;

}
