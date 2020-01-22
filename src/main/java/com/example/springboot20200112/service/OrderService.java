package com.example.springboot20200112.service;

import com.example.springboot20200112.pojo.Order;
import com.example.springboot20200112.request.AddOrderRequest;
import com.example.springboot20200112.request.ChargeBackOrderRequest;
import com.example.springboot20200112.response.OrderResponse;

import java.util.List;

public interface OrderService {

    Integer insertOrder(AddOrderRequest addOrderRequest);

    Integer deleteOrder(Long id);

    Integer chargeBackOrder(ChargeBackOrderRequest chargeBackOrderRequest);

    List<OrderResponse> selectAllOrder(Integer pageNum);

    OrderResponse selectOrderById(Long id);

    List<OrderResponse> selectOrderByUserId(Long userId,Integer pageNum);

    List<OrderResponse> selectOrderByScenicSpotId(Long scenicSpotId,Integer pageNum);
}
