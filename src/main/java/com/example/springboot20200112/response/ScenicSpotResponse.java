package com.example.springboot20200112.response;

import lombok.Data;

@Data
public class ScenicSpotResponse {

    private String scenicSpotName;

    private String scenicSpotAddress;

    private String scenicSpotImg;

    private String scenicSpotDescribe;

    private String scenicSpotImgs;

    private Integer totalScenicSpot;
}
