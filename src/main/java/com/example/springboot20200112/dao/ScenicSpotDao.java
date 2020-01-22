package com.example.springboot20200112.dao;

import com.example.springboot20200112.mapper.ScenicSpotMapper;
import com.example.springboot20200112.pojo.ScenicSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ScenicSpotDao {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    public Integer insertScenicSpot(ScenicSpot scenicSpot){
        return scenicSpotMapper.insert(scenicSpot);
    }

    public Integer deleteScenicSpot(Long id){
        return scenicSpotMapper.deleteByPrimaryKey(id);
    }

    public Integer updateScenicSpot(ScenicSpot scenicSpot){
        return scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot);
    }

    public List<ScenicSpot> selectAllScenicSpot(){
        return scenicSpotMapper.selectAll();
    }

    public List<ScenicSpot> selectScenicSpotByName(String scenicSpotName){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotName","%"+scenicSpotName+"%");
        return scenicSpotMapper.selectByExample(example);
    }

    public List<ScenicSpot> selectScenicSpotByAddress(String scenicSpotAddress){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotAddress","%"+scenicSpotAddress+"%");
        return scenicSpotMapper.selectByExample(example);
    }

    public ScenicSpot selectScenicSpotById(Long id){
        return scenicSpotMapper.selectByPrimaryKey(id);
    }

    public Integer countAllScenicSpot(){
        Example example = new Example(ScenicSpot.class);
        return scenicSpotMapper.selectCountByExample(example);
    }

    public Integer countScenicSpotByName(String scenicSpotName){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotName","%"+scenicSpotName+"%");
        return scenicSpotMapper.selectCountByExample(example);
    }

    public Integer countScenicSpotByAddress(String scenicSpotAddress){
        Example example = new Example(ScenicSpot.class);
        example.createCriteria().andLike("scenicSpotAddress","%"+scenicSpotAddress+"%");
        return scenicSpotMapper.selectCountByExample(example);
    }
}
