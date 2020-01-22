package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.TicketDao;
import com.example.springboot20200112.pojo.Ticket;
import com.example.springboot20200112.request.AddTicketRequest;
import com.example.springboot20200112.request.UpdateTicketRequest;
import com.example.springboot20200112.response.TicketResponse;
import com.example.springboot20200112.service.TicketService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Integer insertTicket(AddTicketRequest addTicketRequest) {
        Ticket ticket = new Ticket();
        ticket.setCreateTime(new Date());
        BeanUtils.copyProperties(addTicketRequest,ticket);
        return ticketDao.insertTicket(ticket);
    }

    @Override
    public Integer deleteTicket(Long id) {
        return ticketDao.deleteTicket(id);
    }

    @Override
    public Integer updateTicket(UpdateTicketRequest updateTicketRequest) {
        Ticket ticket = new Ticket();
        ticket.setUpdateTime(new Date());
        BeanUtils.copyProperties(updateTicketRequest,ticket);
        return ticketDao.updateTicket(ticket);
    }

    @Override
    public Integer updateTicketNum(List<UpdateTicketRequest> updateTicketRequests) {
        List<Ticket> tickets = new ArrayList<>();
        for (UpdateTicketRequest updateTicketRequest : updateTicketRequests){
            Ticket ticket = new Ticket();
            BeanUtils.copyProperties(updateTicketRequest,ticket);
            ticket.setUpdateTime(new Date());
            tickets.add(ticket);
        }
        return ticketDao.updateTicketNum(tickets);
    }

    @Override
    public List<TicketResponse> selectAllTicket(Integer pageNum) {
        List<Ticket> tickets = ticketDao.selectAllTicket();
        PageHelper.startPage(pageNum,10);
        List<TicketResponse> list = new ArrayList<>();
        for(Ticket ticket:tickets){
            TicketResponse ticketResponse = new TicketResponse();
            BeanUtils.copyProperties(ticket,ticketResponse);
            ticketResponse.setTotalTicket(ticketDao.countAllTicket());
            list.add(ticketResponse);
        }
        PageInfo<TicketResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public TicketResponse selectTicketByName(String ticketName) {
        Ticket ticket = ticketDao.selectTicketByName(ticketName);
        TicketResponse ticketResponse = new TicketResponse();
        BeanUtils.copyProperties(ticket,ticketResponse);
        return ticketResponse;
    }

    @Override
    public List<TicketResponse> selectTicketByAdultPrice(Double minPrice, Double maxPrice,Integer pageNum) {
        List<Ticket> tickets = new ArrayList<>();
        if(minPrice==null){
            tickets = ticketDao.selectTicketByAdultPrice(0.0, maxPrice);
        }else if(maxPrice==null){
            tickets = ticketDao.selectTicketByAdultPrice(minPrice, Double.MAX_VALUE);
        }else {
            tickets = ticketDao.selectTicketByAdultPrice(minPrice, maxPrice);
        }
        PageHelper.startPage(pageNum,10);
        List<TicketResponse> list = new ArrayList<>();
        for(Ticket ticket:tickets){
            TicketResponse ticketResponse = new TicketResponse();
            BeanUtils.copyProperties(ticket,ticketResponse);
            ticketResponse.setTotalTicket(ticketDao.countTicketByAdultPrice(minPrice, maxPrice));
            list.add(ticketResponse);
        }
        PageInfo<TicketResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<TicketResponse> selectTicketByStudentPrice(Double minPrice, Double maxPrice,Integer pageNum) {
        List<Ticket> tickets = new ArrayList<>();
        if(minPrice==null){
            tickets = ticketDao.selectTicketByStudentPrice(0.0, maxPrice);
        }else if(maxPrice==null){
            tickets = ticketDao.selectTicketByStudentPrice(minPrice, Double.MAX_VALUE);
        }else {
            tickets = ticketDao.selectTicketByStudentPrice(minPrice, maxPrice);
        }
        PageHelper.startPage(pageNum,10);
        List<TicketResponse> list = new ArrayList<>();
        for(Ticket ticket:tickets){
            TicketResponse ticketResponse = new TicketResponse();
            BeanUtils.copyProperties(ticket,ticketResponse);
            ticketResponse.setTotalTicket(ticketDao.countTicketByStudentPrice(minPrice, maxPrice));
            list.add(ticketResponse);
        }
        PageInfo<TicketResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }
}
