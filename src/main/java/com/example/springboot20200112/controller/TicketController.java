package com.example.springboot20200112.controller;

import com.example.springboot20200112.common.TravelJsonResult;
import com.example.springboot20200112.request.AddTicketRequest;
import com.example.springboot20200112.request.UpdateTicketRequest;
import com.example.springboot20200112.response.TicketResponse;
import com.example.springboot20200112.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //添加门票信息
    @PostMapping(value = "insert/ticket")
    public TravelJsonResult<Integer> insertTicket(@RequestBody AddTicketRequest addTicketRequest) {
        return TravelJsonResult.ok(ticketService.insertTicket(addTicketRequest));
    }

    //删除门票
    @DeleteMapping(value = "delete/ticket")
    public Integer deleteTicket(Long id){
        return ticketService.deleteTicket(id);
    }

    //修改门票信息
    @PutMapping(value = "update/ticket")
    public Integer updateTicket(@RequestBody UpdateTicketRequest updateTicketRequest){
        return ticketService.updateTicket(updateTicketRequest);
    }

    //批量修改门票信息
    @PutMapping(value = "update/some/ticket")
    public Integer updateTicketNum(@RequestBody List<UpdateTicketRequest> updateTicketRequests){
        return ticketService.updateTicketNum(updateTicketRequests);
    }

    //查找所有的门票信息
    @GetMapping(value = "select/all/ticket")
    public List<TicketResponse> selectAllTicket(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return ticketService.selectAllTicket(pageNum);
    }

    //通过门票名查找门票
    @GetMapping(value = "select/ticket/by/name")
    public TicketResponse selectTicketByName(String ticketName){
        return ticketService.selectTicketByName(ticketName);
    }

    //通过成人票价格查找门票信息
    @GetMapping(value = "select/ticket/By/adultPrice")
    public List<TicketResponse> selectTicketByAdultPrice(Double minPrice,Double maxPrice,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return ticketService.selectTicketByAdultPrice(minPrice, maxPrice, pageNum);
    }

    //通过学生票价格查找门票信息
    @GetMapping(value = "select/ticket/by/studentPrice")
    public List<TicketResponse> selectTicketByStudentPrice(Double minPrice,Double maxPrice,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return ticketService.selectTicketByStudentPrice(minPrice, maxPrice, pageNum);
    }

}
