package com.example.springboot20200112.dao;


import com.example.springboot20200112.mapper.UserMapper;
import com.example.springboot20200112.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public User loginUser(String userName, String password){
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("userName",userName)
                .andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    public Integer registerUser(User user){
        return userMapper.insert(user);
    }

    public Integer deleteUser(Long id){
        return userMapper.deleteByPrimaryKey(id);
    }

    public Integer updateUserById(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectOneUserByUserName(String userName){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",userName);
        return userMapper.selectOneByExample(example);
    }

    public List<User> selectAllUser(){
        return userMapper.selectAll();
    }

    public List<User> selectUserByNickName(String nickName){
        Example example = new Example(User.class);
        example.createCriteria().andLike("nickName","%"+nickName+"%");
        return userMapper.selectByExample(example);
    }

    public User selectUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer countAllUser(){
        Example example = new Example(User.class);
        return userMapper.selectCountByExample(example);
    }

}
