package com.example.springboot20200112.request;

import lombok.Data;

import java.util.List;

@Data
public class ScenicSpotRequest {

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotImg;

    private String scenicSpotDescribe;

    private List<String> scenicSpotImgBoList;
}
