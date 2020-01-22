package com.example.springboot20200112.response;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponse {

    private Long id;

    private String userName;

    private String scenicSpotName;

    private Double ticketPriceAdult;

    private Double ticketPriceStudent;

    private Integer buyAdultTicketNum;

    private Integer buyStudentTicketNum;

    private Double totalPrice;

    /**
     * 0表示已退款
     * 1表示交易成功
     */
    private Short orderState;

    private Date createTime;

    private Integer totalOrder;
}
