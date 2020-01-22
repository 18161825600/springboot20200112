package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class ChangePayPasswordRequest {

    private String userName;

    private String password;

    private String newPayPassword;

    private String copyPayPassword;
}
