package com.example.springboot20200112.dao;

import com.example.springboot20200112.mapper.OrderMapper;
import com.example.springboot20200112.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class OrderDao {

    @Autowired
    private OrderMapper orderMapper;

    public Integer insertOrder(Order order){
        return orderMapper.insert(order);
    }

    public Integer deleteOrder(Long id){
        return orderMapper.deleteByPrimaryKey(id);
    }

    public Integer chargeBackOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    public List<Order> selectAllOrder(){
        return orderMapper.selectAll();
    }

    public Order selectOrderById(Long id){
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> selectOrderByUserId(Long userId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId);
        return orderMapper.selectByExample(example);
    }

    public List<Order> selectOrderByScenicSpotId(Long scenicSpotId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return orderMapper.selectByExample(example);
    }

    public Integer countAllOrder(){
        Example example = new Example(Order.class);
        return orderMapper.selectCountByExample(example);
    }

    public Integer countOrderByUserId(Long userId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId",userId);
        return orderMapper.selectCountByExample(example);
    }

    public Integer countOrderByScenicSpotId(Long scenicSpotId){
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return orderMapper.selectCountByExample(example);
    }
}
