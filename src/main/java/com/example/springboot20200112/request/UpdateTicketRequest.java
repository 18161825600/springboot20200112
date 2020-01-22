package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class UpdateTicketRequest {

    private Long id;

    private Double ticketPriceAdult;

    private Double ticketPriceStudent;

    private Integer ticketNumberAdult;

    private Integer ticketNumberStudent;

    private String ticketDescribe;
}
