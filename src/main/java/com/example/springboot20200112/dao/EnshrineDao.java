package com.example.springboot20200112.dao;

import com.example.springboot20200112.mapper.EnshrineMapper;
import com.example.springboot20200112.pojo.Enshrine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class EnshrineDao {

    @Autowired
    private EnshrineMapper enshrineMapper;

    public Integer insertEnshrine(Enshrine enshrine){
        return enshrineMapper.insert(enshrine);
    }

    public Integer deleteEnshrine(List<Long> ids){
        Example example = new Example(Enshrine.class);
        example.createCriteria().andIn("id",ids);
        return enshrineMapper.deleteByExample(example);
    }

    public List<Enshrine> selectAllEnshrine(){
        return enshrineMapper.selectAll();
    }

    public List<Enshrine> selectEnshrineByUserId(Long userId){
        Example example = new Example(Enshrine.class);
        example.createCriteria().andEqualTo("userId",userId);
        return enshrineMapper.selectByExample(example);
    }

    public List<Enshrine> selectEnshrineByScenicSpotId(Long scenicSpotId){
        Example example = new Example(Enshrine.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return enshrineMapper.selectByExample(example);
    }

    public Integer countAllEnshrine(){
        Example example = new Example(Enshrine.class);
        return enshrineMapper.selectCountByExample(example);
    }

    public Integer countEnshrineByUserId(Long userId){
        Example example = new Example(Enshrine.class);
        example.createCriteria().andEqualTo("userId",userId);
        return enshrineMapper.selectCountByExample(example);
    }

    public Integer countEnshrineByScenicSpotId(Long scenicSpotId) {
        Example example = new Example(Enshrine.class);
        example.createCriteria().andEqualTo("scenicSpotId", scenicSpotId);
        return enshrineMapper.selectCountByExample(example);
    }
}
