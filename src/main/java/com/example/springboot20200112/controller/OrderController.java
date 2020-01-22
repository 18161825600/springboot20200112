package com.example.springboot20200112.controller;

import com.example.springboot20200112.request.AddOrderRequest;
import com.example.springboot20200112.request.ChargeBackOrderRequest;
import com.example.springboot20200112.response.OrderResponse;
import com.example.springboot20200112.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //下订单
    @PostMapping(value = "insert/order")
    public String insertOrder(@RequestBody AddOrderRequest addOrderRequest){
        Integer integer = orderService.insertOrder(addOrderRequest);
        if(integer==1){
            return "交易成功!";
        }else if(integer==0){
            return "请选择买票的张数!";
        }else if(integer==-1){
            return "成人票剩余不足!";
        }else if(integer==-2){
            return "学生票剩余不足!";
        }else if(integer==-3){
            return "成人票和学生票剩余都不足!";
        }else if(integer==-4){
            return "支付密码错误!";
        }else if(integer==-5){
            return "余额不足!";
        }else return "服务器错误，请稍后重试！";
    }

    //删除订单
    @DeleteMapping(value = "delete/order")
    public Integer deleteOrder(Long id){
        return orderService.deleteOrder(id);
    }

    //退单
    @PutMapping(value = "chargeBack/order")
    public String chargeBackOrder(@RequestBody ChargeBackOrderRequest chargeBackOrderRequest){
        Integer integer = orderService.chargeBackOrder(chargeBackOrderRequest);
        if(integer==1){
            return "退单成功";
        }else if(integer==0){
            return "订单已退款，请勿重复操作！";
        }else if(integer==-1){
            return "门票已过期，无法退款";
        }else if(integer==-2){
            return "支付密码错误，无法退款，请重新输入支付密码！";
        }else return "服务器错误，请稍后再试！";
    }

    //查询数据库里的所有订单
    @GetMapping(value = "select/all/order")
    public List<OrderResponse> selectAllOrder(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return orderService.selectAllOrder(pageNum);
    }

    //根据订单id查找订单
    @GetMapping(value = "select/order/by/id")
    public OrderResponse selectOrderById(Long id){
        return orderService.selectOrderById(id);
    }

    //根据用户id查找该用户的所有订单
    @GetMapping(value = "select/order/by/userId")
    public List<OrderResponse> selectOrderByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return orderService.selectOrderByUserId(userId, pageNum);
    }

    //根据景点id查找该景点的所有订单
    @GetMapping(value = "select/order/by/scenicSpotId")
    public List<OrderResponse> selectOrderByScenicSpotId(Long scenicSpotId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return orderService.selectOrderByScenicSpotId(scenicSpotId, pageNum);
    }
}
