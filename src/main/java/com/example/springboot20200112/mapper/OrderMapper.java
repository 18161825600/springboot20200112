package com.example.springboot20200112.mapper;


import com.example.springboot20200112.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends CommonMapper<Order> {
}