package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class InvestMoneyRequest {

    private String userName;

    private String payPassword;

    private Double money;
}
