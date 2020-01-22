package com.example.springboot20200112.mapper;

import com.example.springboot20200112.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends CommonMapper<User> {
}