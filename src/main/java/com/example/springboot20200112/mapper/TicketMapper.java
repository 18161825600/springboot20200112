package com.example.springboot20200112.mapper;


import com.example.springboot20200112.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper extends CommonMapper<Ticket> {

     Integer updateTicketNum(List<Ticket> tickets);
}