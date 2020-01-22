package com.example.springboot20200112.service;

import com.example.springboot20200112.pojo.ScenicSpot;
import com.example.springboot20200112.request.ScenicSpotRequest;
import com.example.springboot20200112.response.ScenicSpotResponse;

import java.util.List;

public interface ScenicSpotService {

    Integer insertScenicSpot(ScenicSpotRequest scenicSpotRequest);

    Integer deleteScenicSpot(Long id);

    Integer updateScenicSpot(ScenicSpotRequest scenicSpotRequest);

    List<ScenicSpotResponse> selectAllScenicSpot(Integer pageNum);

    List<ScenicSpotResponse> selectScenicSpotByName(String scenicSpotName,Integer pageNum);

    List<ScenicSpotResponse> selectScenicSpotByAddress(String scenicSpotAddress,Integer pageNum);

}
