package com.example.springboot20200112.controller;


import com.example.springboot20200112.common.TravelJsonResult;
import com.example.springboot20200112.dao.UserDao;
import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.*;
import com.example.springboot20200112.response.UserResponse;
import com.example.springboot20200112.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    //登录
    @GetMapping(value = "login/user")
    public TravelJsonResult<String> loginUser(String userName,String password){
        User user = userService.loginUser(userName, password);
        if(user!=null){
            return TravelJsonResult.ok("登录成功");
        }else return TravelJsonResult.errorMsg("用户名或密码错误");
    }

    //注册
    @PostMapping(value = "register/user")
    public TravelJsonResult<String> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        try {
            Integer integer = userService.registerUser(registerUserRequest);
            if (integer == 1) {
                User user = userDao.selectOneUserByUserName(registerUserRequest.getUserName());
                return TravelJsonResult.ok("注册成功，你的安全码是：" + user.getSafetyCode());
            }
        }catch (Exception e){
            return TravelJsonResult.errorException("用户名已存在");
        }return TravelJsonResult.errorTokenMsg("服务器错误");
    }

    //删除用户
    @DeleteMapping(value = "delete/user/by/id")
    public TravelJsonResult<String> deleteUser(Long id){
        Integer integer = userService.deleteUser(id);
        if(integer==1){
            return TravelJsonResult.ok("删除成功");
        }else return TravelJsonResult.errorMsg("删除失败");
    }

    //通过用户名查找用户
    @GetMapping(value = "select/user/by/username")
    public TravelJsonResult<UserResponse> selectOneUserByUserName(String userName){
        try {
            UserResponse userResponse = userService.selectOneUserByUserName(userName);
            if (userResponse != null) {
                return TravelJsonResult.ok(userResponse);
            }
        }catch (Exception e){
            return TravelJsonResult.errorMsg("用户不存在");
        }
        return TravelJsonResult.errorMsg("服务器错误");
    }

    //通过主键更新用户信息
    @PutMapping(value = "update/user/by/id")
    public TravelJsonResult<String> updateUserById(@RequestBody UpdateUserRequest updateUserRequest){
        Integer integer = userService.updateUserById(updateUserRequest);
        if(integer==1){
            return TravelJsonResult.ok("更新成功");
        }else return TravelJsonResult.errorMsg("更新失败");
    }

    //修改用户密码
    @PutMapping(value = "change/user/password")
    public TravelJsonResult<String> changeUserPassword(ChangeUserPasswordRequest changeUserPasswordRequest){
        Integer integer = userService.changeUserPassword(changeUserPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok("密码修改成功");
        }else if(integer==0){
            return TravelJsonResult.errorMsg("两次输入的密码不一致");
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("新密码不能与旧密码一致");
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("安全码错误");
        }else return TravelJsonResult.errorMsg("用户名错误");
    }

    //修改支付密码
    @PutMapping(value = "change/user/payPassword")
    public TravelJsonResult<String> changePayPassword(ChangePayPasswordRequest changePayPasswordRequest){
        Integer integer = userService.changePayPassword(changePayPasswordRequest);
        if(integer==1){
            return TravelJsonResult.ok("支付密码更改成功");
        }else if(integer==0){
            return TravelJsonResult.errorMsg("用户名错误");
        }else if(integer==-1){
            return TravelJsonResult.errorMsg("用户登录密码错误");
        }else if(integer==-2){
            return TravelJsonResult.errorMsg("新的支付密码不能与旧密码相同");
        }else return TravelJsonResult.errorMsg("两次输入的支付密码不一致");
    }

    //修改用户手机号
    @PutMapping(value = "change/user/phonenum")
    public TravelJsonResult<String> changeUserPhoneNum(ChangeUserPhoneNumRequest changeUserPhoneNumRequest){
        Integer integer = userService.changeUserPhoneNum(changeUserPhoneNumRequest);
        if(integer==1){
            return TravelJsonResult.ok("更改成功");
        }else if(integer==0){
            return TravelJsonResult.errorMsg("密码错误");
        }else return TravelJsonResult.errorMsg("用户名错误");
    }

    //充钱
    @PutMapping(value = "invest/money")
    public TravelJsonResult<String> investMoney(InvestMoneyRequest investMoneyRequest){
        Integer integer = userService.investMoney(investMoneyRequest);
        if(integer==1){
            return TravelJsonResult.ok("充值成功!");
        }else if(integer==0){
            return TravelJsonResult.errorMsg("用户名错误");
        }else return TravelJsonResult.errorMsg("支付密码错误");
    }

    //查看用户余额
    @GetMapping(value = "select/user/lastMoney")
    public TravelJsonResult<Double> selectLastMoney(String userName){
        return TravelJsonResult.ok(userService.selectLastMoneyByUserName(userName)+"元") ;
    }

    //查找全部用户
    @GetMapping(value = "select/all/user")
    public TravelJsonResult<PageInfo<UserResponse>> selectAllUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return TravelJsonResult.ok(userService.selectAllUser(pageNum));
    }

    //通过用户名查找用户
    @GetMapping(value = "select/user/by/nickname")
    public TravelJsonResult<UserResponse> selectUserByNickName(String nickName){
        return TravelJsonResult.ok(userService.selectUserByNickName(nickName));
    }

    //查找用户数量有多少
    @GetMapping(value = "count/all/user")
    public TravelJsonResult<Integer> countAllUser(){
        return TravelJsonResult.ok(userService.countAllUser()+"个用户");
    }
}
