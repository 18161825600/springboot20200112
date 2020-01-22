package com.example.springboot20200112.request;


import lombok.Data;

@Data
public class UpdateUserRequest {

    private Long id;

    private String nickName;

    private String userImg;

    private Short isStudent;
}
