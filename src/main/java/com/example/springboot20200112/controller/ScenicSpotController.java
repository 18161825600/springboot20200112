package com.example.springboot20200112.controller;

import com.example.springboot20200112.request.ScenicSpotRequest;
import com.example.springboot20200112.response.ScenicSpotResponse;
import com.example.springboot20200112.service.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    //添加景点
    @PostMapping(value = "insert/scenicSpot")
    public Integer insertScenicSpot(@RequestBody ScenicSpotRequest scenicSpotRequest){
        return scenicSpotService.insertScenicSpot(scenicSpotRequest);
    }

    //用主键删除景点
    @DeleteMapping(value = "delete/scenicSpot")
    public Integer deleteScenicSpot(Long id){
        return scenicSpotService.deleteScenicSpot(id);
    }

    //修改景点信息
    @PutMapping(value = "update/scenicSpot")
    public Integer updateScenicSpot(@RequestBody ScenicSpotRequest scenicSpotRequest){
        return scenicSpotService.updateScenicSpot(scenicSpotRequest);
    }

    //查找全部景点
    @GetMapping(value = "select/all/scenicSpot")
    public List<ScenicSpotResponse> selectAllScenicSpot(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return scenicSpotService.selectAllScenicSpot(pageNum);
    }

    //根据景点名模糊查找景点
    @GetMapping(value = "select/scenicSpot/by/name")
    public List<ScenicSpotResponse> selectScenicSpotByName(String scenicSpotName,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return scenicSpotService.selectScenicSpotByName(scenicSpotName, pageNum);
    }

    //根据位置查找景点
    @GetMapping(value = "select/scenicSpot/by/address")
    public List<ScenicSpotResponse> selectScenicSpotByAddress(String scenicSpotAddress,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return scenicSpotService.selectScenicSpotByAddress(scenicSpotAddress, pageNum);
    }
}
