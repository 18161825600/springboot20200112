package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.UserDao;
import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.*;
import com.example.springboot20200112.response.UserResponse;
import com.example.springboot20200112.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loginUser(String userName, String password) {
        return userDao.loginUser(userName, password);
    }

    @Override
    public Integer registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerUserRequest,user);
        user.setCreateTime(new Date());
        user.setSafetyCode(randomSafetyCode());
        return userDao.registerUser(user);
    }

    @Override
    public Integer deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public UserResponse selectOneUserByUserName(String userName) {
        User user = userDao.selectOneUserByUserName(userName);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return userResponse;
    }

    @Override
    public Integer updateUserById(UpdateUserRequest updateUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(updateUserRequest,user);
        user.setUpdateTime(new Date());
        return userDao.updateUserById(user);
    }

    @Override
    public Integer changeUserPassword(ChangeUserPasswordRequest changeUserPasswordRequest) {
        User user = userDao.selectOneUserByUserName(changeUserPasswordRequest.getUserName());
        if(user!=null) {
            if (changeUserPasswordRequest.getSafetyCode().equals(user.getSafetyCode())) {
                if (changeUserPasswordRequest.getNewPassword().equals(user.getPassword())) {
                    return -1;//新密码不能与旧密码相同
                } else if (!changeUserPasswordRequest.getNewPassword().equals(changeUserPasswordRequest.getCopyPassword())) {
                    return 0;//两次密码不一致
                } else {
                    user.setPassword(changeUserPasswordRequest.getNewPassword());
                    user.setUpdateTime(new Date());
                    return userDao.updateUserById(user);//更改成功
                }
            } else
                return -2;//安全码错误
        }else return -3;//用户不存在
    }

    @Override
    public Integer changePayPassword(ChangePayPasswordRequest changePayPasswordRequest) {
        User user = userDao.selectOneUserByUserName(changePayPasswordRequest.getUserName());
        if(user==null){
            return 0;//用户名错误
        }else {
            if(changePayPasswordRequest.getPassword().equals(user.getPassword())){
                if(changePayPasswordRequest.getNewPayPassword().equals(user.getPayPassword())){
                    return -2;//新的支付密码不能和旧密码一样
                }else {
                    if(changePayPasswordRequest.getNewPayPassword().equals(changePayPasswordRequest.getCopyPayPassword())){
                        user.setUpdateTime(new Date());
                        user.setPayPassword(changePayPasswordRequest.getNewPayPassword());
                        userDao.updateUserById(user);
                        return 1;//更改成功
                    }else return -3;//两次密码不一致
                }
            }else return -1;//用户密码错误
        }
    }

    @Override
    public Integer changeUserPhoneNum(ChangeUserPhoneNumRequest changeUserPhoneNumRequest) {
        User user = userDao.selectOneUserByUserName(changeUserPhoneNumRequest.getUserName());
        if(user!=null){
            if(changeUserPhoneNumRequest.getPassword().equals(user.getPassword())){
                user.setPhoneNum(changeUserPhoneNumRequest.getPhoneNum());
                user.setUpdateTime(new Date());
                return userDao.updateUserById(user);
            }else return 0;//密码错误
        }else return -1;//用户名错误
    }

    @Override
    public Integer investMoney(InvestMoneyRequest investMoneyRequest) {
        User user = userDao.selectOneUserByUserName(investMoneyRequest.getUserName());
        if(user==null){
            return 0;//用户名错误
        }else {
            if(investMoneyRequest.getPayPassword().equals(user.getPayPassword())){
                if(user.getLastMoney()==null){
                    user.setLastMoney(investMoneyRequest.getMoney());
                }else {
                    user.setLastMoney(user.getLastMoney() + investMoneyRequest.getMoney());
                }
                user.setUpdateTime(new Date());
               return userDao.updateUserById(user);
            }else return -1;//支付密码错误
        }
    }

    @Override
    public PageInfo<UserResponse> selectAllUser(Integer pageNum) {
        List<User> users = userDao.selectAllUser();
        if(pageNum==null){
            PageHelper.startPage(1,10);
        }else {
            PageHelper.startPage(pageNum,10);
        }
        List<UserResponse> userResponseList = searchUser(users);
        PageInfo<UserResponse> pageInfo = new PageInfo<>(userResponseList);
        return pageInfo;
    }

    @Override
    public List<UserResponse> selectUserByNickName(String nickName) {
        List<User> users = userDao.selectUserByNickName(nickName);
        return searchUser(users);
    }

    @Override
    public Double selectLastMoneyByUserName(String userName) {
        User user = userDao.selectOneUserByUserName(userName);
        return user.getLastMoney();
    }

    @Override
    public Integer countAllUser() {
        return userDao.countAllUser();
    }


    //生成一个由数字和大小写字母组成的八位数字符串
    public String randomSafetyCode(){
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int count = 0;
        int i;
        int maxNum = 62;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while(count < 8){
            i = Math.abs(random.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                stringBuffer.append(str[i]);
                count ++;
            }
        }
        return stringBuffer.toString();
    }

    //user转换成userResponse
    public List<UserResponse> searchUser(List<User> users){
        List<UserResponse> list = new ArrayList<>();
        for (User user:users) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user,userResponse);
            list.add(userResponse);
        }
        return list;
    }
}
