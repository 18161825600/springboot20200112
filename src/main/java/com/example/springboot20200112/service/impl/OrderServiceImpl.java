package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.OrderDao;
import com.example.springboot20200112.dao.ScenicSpotDao;
import com.example.springboot20200112.dao.TicketDao;
import com.example.springboot20200112.dao.UserDao;
import com.example.springboot20200112.pojo.Order;
import com.example.springboot20200112.pojo.ScenicSpot;
import com.example.springboot20200112.pojo.Ticket;
import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.AddOrderRequest;
import com.example.springboot20200112.request.ChargeBackOrderRequest;
import com.example.springboot20200112.response.OrderResponse;
import com.example.springboot20200112.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertOrder(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        Ticket ticket = ticketDao.selectTicketById(addOrderRequest.getTicketId());
        User user = userDao.selectUserById(addOrderRequest.getUserId());
        if(addOrderRequest.getBuyAdultTicketNum()==null && addOrderRequest.getBuyStudentTicketNum()!=null){
            order.setBuyStudentTicketNum(addOrderRequest.getBuyStudentTicketNum());
            order.setTotalPrice(addOrderRequest.getBuyStudentTicketNum()*ticket.getTicketPriceStudent());
        }else if(addOrderRequest.getBuyAdultTicketNum()!=null && addOrderRequest.getBuyStudentTicketNum()==null){
            order.setBuyAdultTicketNum(addOrderRequest.getBuyAdultTicketNum());
            order.setTotalPrice(addOrderRequest.getBuyAdultTicketNum()*ticket.getTicketPriceAdult());
        }else if(addOrderRequest.getBuyAdultTicketNum()==null && addOrderRequest.getBuyStudentTicketNum()==null){
            return 0;//未选择买票张数
        }else{
            order.setBuyStudentTicketNum(addOrderRequest.getBuyStudentTicketNum());
            order.setBuyAdultTicketNum(addOrderRequest.getBuyAdultTicketNum());
            order.setTotalPrice(addOrderRequest.getBuyAdultTicketNum()*ticket.getTicketPriceAdult()+addOrderRequest.getBuyStudentTicketNum()*ticket.getTicketPriceStudent());
            if(ticket.getTicketNumberAdult()<addOrderRequest.getBuyAdultTicketNum() && ticket.getTicketNumberStudent()>addOrderRequest.getBuyStudentTicketNum() ){
                return -1;//成人票剩余张数不足
            }else if(ticket.getTicketNumberAdult()>addOrderRequest.getBuyAdultTicketNum() && ticket.getTicketNumberStudent()<addOrderRequest.getBuyStudentTicketNum()){
                return -2;//剩余学生票张数不足
            }else if(ticket.getTicketNumberAdult()<addOrderRequest.getBuyAdultTicketNum() && ticket.getTicketNumberStudent()<addOrderRequest.getBuyStudentTicketNum()){
                return -3;//剩余成人票和学生票都不足
            }else {
                if(!addOrderRequest.getPayPassword().equals(user.getPayPassword())){
                    return -4;//支付密码错误
                }else{
                    if(user.getLastMoney()<order.getTotalPrice()){
                        return -5;//用户余额不足
                    }else{
                        user.setLastMoney(user.getLastMoney()-order.getTotalPrice());
                        user.setUpdateTime(new Date());
                        userDao.updateUserById(user);

                        ticket.setTicketNumberAdult(ticket.getTicketNumberAdult()-addOrderRequest.getBuyAdultTicketNum());
                        ticket.setTicketNumberStudent(ticket.getTicketNumberStudent()-addOrderRequest.getBuyStudentTicketNum());
                        ticket.setUpdateTime(new Date());
                        ticketDao.updateTicket(ticket);

                        order.setUserId(addOrderRequest.getUserId());
                        order.setTicketId(addOrderRequest.getTicketId());
                        order.setCreateTime(new Date());
                        order.setOrderState((short)1);
                        return orderDao.insertOrder(order);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Integer deleteOrder(Long id) {
        return orderDao.deleteOrder(id);
    }

    @Override
    public Integer chargeBackOrder(ChargeBackOrderRequest chargeBackOrderRequest) {
        Order order = orderDao.selectOrderById(chargeBackOrderRequest.getOrderId());
        User user = userDao.selectUserById(order.getUserId());
        if(user.getPayPassword().equals(chargeBackOrderRequest.getPayPassword())){
            if(order.getOrderState()==0){
                return 0;//已退单
            }else{
                Date createTime = order.getCreateTime();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                boolean equals = fmt.format(createTime).equals(fmt.format(new Date()));
                if(equals==false){
                    return -1;//票过期
                }else {
                    Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
                    ticket.setTicketNumberAdult(ticket.getTicketNumberAdult()+order.getBuyAdultTicketNum());
                    ticket.setTicketNumberStudent(ticket.getTicketNumberStudent()+order.getBuyStudentTicketNum());
                    ticket.setUpdateTime(new Date());
                    ticketDao.updateTicket(ticket);

                    user.setLastMoney(user.getLastMoney()+order.getTotalPrice());
                    user.setUpdateTime(new Date());
                    userDao.updateUserById(user);

                    order.setOrderState((short)0);
                    order.setUpdateTime(new Date());
                    return orderDao.chargeBackOrder(order);
                }
            }
        }else {
            return -2;//支付密码错误
        }
    }

    @Override
    public List<OrderResponse> selectAllOrder(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Order> orders = orderDao.selectAllOrder();
        List<OrderResponse> list = new ArrayList<>();
        for (Order order:orders) {
            OrderResponse orderResponse = changeOrderToOrderResponse(order);
            orderResponse.setTotalOrder(orderDao.countAllOrder());
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public OrderResponse selectOrderById(Long id) {
        Order order = orderDao.selectOrderById(id);
        return changeOrderToOrderResponse(order);
    }

    @Override
    public List<OrderResponse> selectOrderByUserId(Long userId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Order> orders = orderDao.selectOrderByUserId(userId);
        List<OrderResponse> list = new ArrayList<>();
        for (Order order:orders) {
            OrderResponse orderResponse = changeOrderToOrderResponse(order);
            orderResponse.setTotalOrder(orderDao.countOrderByUserId(userId));
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<OrderResponse> selectOrderByScenicSpotId(Long scenicSpotId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Order> orders = orderDao.selectOrderByScenicSpotId(scenicSpotId);
        List<OrderResponse> list = new ArrayList<>();
        for (Order order:orders) {
            OrderResponse orderResponse = changeOrderToOrderResponse(order);
            orderResponse.setTotalOrder(orderDao.countOrderByScenicSpotId(scenicSpotId));
            list.add(orderResponse);
        }
        PageInfo<OrderResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }


    public OrderResponse changeOrderToOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order,orderResponse);

        User user = userDao.selectUserById(order.getUserId());
        orderResponse.setUserName(user.getUserName());

        Ticket ticket = ticketDao.selectTicketById(order.getTicketId());
        orderResponse.setTicketPriceStudent(ticket.getTicketPriceStudent());
        orderResponse.setTicketPriceAdult(ticket.getTicketPriceAdult());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(ticket.getScenicSpotId());
        orderResponse.setScenicSpotName(scenicSpot.getScenicSpotName());

        return orderResponse;
    }
}
