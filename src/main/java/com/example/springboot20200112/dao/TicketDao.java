package com.example.springboot20200112.dao;

import com.example.springboot20200112.mapper.TicketMapper;
import com.example.springboot20200112.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class TicketDao {

    @Autowired
    private TicketMapper ticketMapper;

    public Integer insertTicket(Ticket ticket){
        return ticketMapper.insert(ticket);
    }

    public Integer deleteTicket(Long id){
        Example example = new Example(Ticket.class);
        example.createCriteria().andEqualTo("id",id);
        return ticketMapper.deleteByPrimaryKey(example);
    }

    public Integer updateTicket(Ticket ticket){
        return ticketMapper.updateByPrimaryKeySelective(ticket);
    }

    public Integer updateTicketNum(List<Ticket> tickets){
        return ticketMapper.updateTicketNum(tickets);
    }

    public List<Ticket> selectAllTicket(){
        return ticketMapper.selectAll();
    }

    public Ticket selectTicketById(Long id){
        return ticketMapper.selectByPrimaryKey(id);
    }

    public Ticket selectTicketByName(String ticketName){
        Example example = new Example(Ticket.class);
        example.createCriteria().andEqualTo("ticketName",ticketName);
        return ticketMapper.selectOneByExample(example);
    }

    public List<Ticket> selectTicketByAdultPrice(Double minPrice,Double maxPrice){
        Example example = new Example(Ticket.class);
        example.createCriteria().andBetween("ticketPriceAdult",minPrice,maxPrice);
        return ticketMapper.selectByExample(example);
    }

    public List<Ticket> selectTicketByStudentPrice(Double minPrice,Double maxPrice){
        Example example = new Example(Ticket.class);
        example.createCriteria().andBetween("ticketPriceStudent",minPrice,maxPrice);
        return ticketMapper.selectByExample(example);
    }

    public Integer countAllTicket(){
        Example example = new Example(Ticket.class);
        return ticketMapper.selectCountByExample(example);
    }

    public Integer countTicketByAdultPrice(Double minPrice,Double maxPrice){
        Example example = new Example(Ticket.class);
        example.createCriteria().andBetween("ticketPriceAdult",minPrice,maxPrice);
        return ticketMapper.selectCountByExample(example);
    }

    public Integer countTicketByStudentPrice(Double minPrice,Double maxPrice) {
        Example example = new Example(Ticket.class);
        example.createCriteria().andBetween("ticketPriceStudent", minPrice, maxPrice);
        return ticketMapper.selectCountByExample(example);
    }

}
