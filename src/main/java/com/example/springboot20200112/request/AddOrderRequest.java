package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class AddOrderRequest {

    private Long userId;

    private Long ticketId;

    private Integer buyAdultTicketNum;

    private Integer buyStudentTicketNum;

    private String payPassword;
}
