package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class ChargeBackOrderRequest {

    private Long orderId;

    private String payPassword;
}
