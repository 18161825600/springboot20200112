package com.example.springboot20200112.service;

import com.example.springboot20200112.request.AddTicketRequest;
import com.example.springboot20200112.request.UpdateTicketRequest;
import com.example.springboot20200112.response.TicketResponse;

import java.util.List;

public interface TicketService {

    Integer insertTicket(AddTicketRequest addTicketRequest);

    Integer deleteTicket(Long id);

    Integer updateTicket(UpdateTicketRequest updateTicketRequest);

    Integer updateTicketNum(List<UpdateTicketRequest> updateTicketRequests);

    List<TicketResponse> selectAllTicket(Integer pageNum);

    TicketResponse selectTicketByName(String ticketName);

    List<TicketResponse> selectTicketByAdultPrice(Double minPrice,Double maxPrice,Integer pageNum);

    List<TicketResponse> selectTicketByStudentPrice(Double minPrice,Double maxPrice,Integer pageNum);
}
