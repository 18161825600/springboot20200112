package com.example.springboot20200112.mapper;


import com.example.springboot20200112.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends CommonMapper<Comment> {
}