package com.example.springboot20200112.service;

import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.*;
import com.example.springboot20200112.response.UserResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    User loginUser(String userName, String password);

    Integer registerUser(RegisterUserRequest registerUserRequest);

    Integer deleteUser(Long id);

    UserResponse selectOneUserByUserName(String userName);

    Integer updateUserById(UpdateUserRequest updateUserRequest);

    Integer changeUserPassword(ChangeUserPasswordRequest changeUserPasswordRequest);

    Integer changePayPassword(ChangePayPasswordRequest changePayPasswordRequest);

    Integer changeUserPhoneNum(ChangeUserPhoneNumRequest changeUserPhoneNumRequest);

    Integer investMoney(InvestMoneyRequest investMoneyRequest);

    PageInfo<UserResponse> selectAllUser(Integer pageNum);

    List<UserResponse> selectUserByNickName(String nickName);

    Double selectLastMoneyByUserName(String userName);

    Integer countAllUser();

}
