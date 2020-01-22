package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class AddTicketRequest {

    private Long scenicSpotId;

    private String ticketName;

    private Double ticketPriceAdult;

    private Double ticketPriceStudent;

    private Integer ticketNumberAdult;

    private Integer ticketNumberStudent;

    private String ticketDescribe;
}
