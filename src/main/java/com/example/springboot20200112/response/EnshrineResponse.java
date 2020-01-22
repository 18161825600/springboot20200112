package com.example.springboot20200112.response;

import lombok.Data;

import java.util.Date;

@Data
public class EnshrineResponse {

    private String nickName;

    private String scenicSpotName;

    private String scenicSpotImg;

    private String scenicSpotDescribe;

    private Date createTime;

    private Integer totalEnshrine;
}
