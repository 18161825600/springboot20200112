package com.example.springboot20200112.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "last_money")
    private Double lastMoney;

    @Column(name = "is_student")
    private Short isStudent;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "safety_code")
    private String safetyCode;

    @Column(name = "pay_password")
    private String payPassword;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return phone_num
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * @return last_money
     */
    public Double getLastMoney() {
        return lastMoney;
    }

    /**
     * @param lastMoney
     */
    public void setLastMoney(Double lastMoney) {
        this.lastMoney = lastMoney;
    }

    /**
     * @return is_student
     */
    public Short getIsStudent() {
        return isStudent;
    }

    /**
     * @param isStudent
     */
    public void setIsStudent(Short isStudent) {
        this.isStudent = isStudent;
    }

    /**
     * @return user_img
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * @param userImg
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

    /**
     * @return safety_code
     */
    public String getSafetyCode() {
        return safetyCode;
    }

    /**
     * @param safetyCode
     */
    public void setSafetyCode(String safetyCode) {
        this.safetyCode = safetyCode == null ? null : safetyCode.trim();
    }

    /**
     * @return pay_password
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     * @param payPassword
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}