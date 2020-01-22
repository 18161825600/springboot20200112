package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.ScenicSpotDao;
import com.example.springboot20200112.pojo.ScenicSpot;
import com.example.springboot20200112.request.ScenicSpotRequest;
import com.example.springboot20200112.response.ScenicSpotResponse;
import com.example.springboot20200112.service.ScenicSpotService;
import com.example.springboot20200112.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertScenicSpot(ScenicSpotRequest scenicSpotRequest) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(scenicSpotRequest,scenicSpot);
        scenicSpot.setCreateTime(new Date());
        List<String> scenicSpotImgBoList = scenicSpotRequest.getScenicSpotImgBoList();
        if(!CollectionUtils.isEmpty(scenicSpotImgBoList)){
            HashMap<String,String> hashMap = new HashMap<>();
            for (String s:scenicSpotImgBoList) {
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,s);
            }
            scenicSpot.setScenicSpotImgs(JsonUtils.objectToJson(hashMap));
        }
        return scenicSpotDao.insertScenicSpot(scenicSpot);
    }

    @Override
    public Integer deleteScenicSpot(Long id) {
        return scenicSpotDao.deleteScenicSpot(id);
    }

    @Override
    public Integer updateScenicSpot(ScenicSpotRequest scenicSpotRequest) {
        ScenicSpot scenicSpot = new ScenicSpot();
        BeanUtils.copyProperties(scenicSpotRequest,scenicSpot);
        scenicSpot.setUpdateTime(new Date());
        List<String> scenicSpotImgBoList = scenicSpotRequest.getScenicSpotImgBoList();
        if(!CollectionUtils.isEmpty(scenicSpotImgBoList)){
            HashMap<String,String> hashMap = new HashMap<>();
            for(String s:scenicSpotImgBoList){
                String uuid = UUID.randomUUID().toString();
                hashMap.put(uuid,s);
            }
            scenicSpot.setScenicSpotImgs(JsonUtils.objectToJson(hashMap));
        }
        return scenicSpotDao.updateScenicSpot(scenicSpot);
    }

    @Override
    public List<ScenicSpotResponse> selectAllScenicSpot(Integer pageNum) {
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectAllScenicSpot();
        PageHelper.startPage(pageNum,10);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for (ScenicSpot scenicSpot:scenicSpots) {
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotalScenicSpot(scenicSpotDao.countAllScenicSpot());
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<ScenicSpotResponse> selectScenicSpotByName(String scenicSpotName,Integer pageNum) {
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectScenicSpotByName(scenicSpotName);
        PageHelper.startPage(pageNum,10);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for (ScenicSpot scenicSpot:scenicSpots) {
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotalScenicSpot(scenicSpotDao.countScenicSpotByName(scenicSpotName));
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<ScenicSpotResponse> selectScenicSpotByAddress(String scenicSpotAddress,Integer pageNum) {
        List<ScenicSpot> scenicSpots = scenicSpotDao.selectScenicSpotByAddress(scenicSpotAddress);
        PageHelper.startPage(pageNum,10);
        List<ScenicSpotResponse> list = new ArrayList<>();
        for (ScenicSpot scenicSpot:scenicSpots) {
            ScenicSpotResponse scenicSpotResponse = new ScenicSpotResponse();
            BeanUtils.copyProperties(scenicSpot,scenicSpotResponse);
            scenicSpotResponse.setTotalScenicSpot(scenicSpotDao.countScenicSpotByAddress(scenicSpotAddress));
            list.add(scenicSpotResponse);
        }
        PageInfo<ScenicSpotResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }


}
