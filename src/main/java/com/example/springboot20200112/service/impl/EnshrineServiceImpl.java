package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.EnshrineDao;
import com.example.springboot20200112.dao.ScenicSpotDao;
import com.example.springboot20200112.dao.UserDao;
import com.example.springboot20200112.pojo.Enshrine;
import com.example.springboot20200112.pojo.ScenicSpot;
import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.AddEnshrineRequest;
import com.example.springboot20200112.response.EnshrineResponse;
import com.example.springboot20200112.service.EnshrineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class EnshrineServiceImpl implements EnshrineService {

    @Autowired
    private EnshrineDao enshrineDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertEnshrine(AddEnshrineRequest addEnshrineRequest) {
        Enshrine enshrine = new Enshrine();
        enshrine.setCreateTime(new Date());
        BeanUtils.copyProperties(addEnshrineRequest,enshrine);
        return enshrineDao.insertEnshrine(enshrine);
    }

    @Override
    public Integer deleteEnshrine(List<Long> ids) {
        return enshrineDao.deleteEnshrine(ids);
    }

    @Override
    public PageInfo<EnshrineResponse> selectAllEnshrine(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Enshrine> enshrines = enshrineDao.selectAllEnshrine();

        List<EnshrineResponse> list = new ArrayList<>();
        for (Enshrine enshrine : enshrines) {

            EnshrineResponse enshrineResponse = changeEnshrineToEnshrineResponse(enshrine);
            enshrineResponse.setTotalEnshrine(enshrineDao.countAllEnshrine());

            list.add(enshrineResponse);
        }
        PageInfo<EnshrineResponse> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<EnshrineResponse> selectEnshrineByUserId(Long userId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Enshrine> enshrines = enshrineDao.selectEnshrineByUserId(userId);
        List<EnshrineResponse> list = new ArrayList<>();
        for (Enshrine enshrine:enshrines) {
            EnshrineResponse enshrineResponse = changeEnshrineToEnshrineResponse(enshrine);
            enshrineResponse.setTotalEnshrine(enshrineDao.countEnshrineByUserId(userId));
            list.add(enshrineResponse);
        }
        PageInfo<EnshrineResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<EnshrineResponse> selectEnshrineByScenicSpotId(Long scenicSpotId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Enshrine> enshrines = enshrineDao.selectEnshrineByScenicSpotId(scenicSpotId);
        List<EnshrineResponse> list = new ArrayList<>();
        for (Enshrine enshrine:enshrines) {
            EnshrineResponse enshrineResponse = changeEnshrineToEnshrineResponse(enshrine);
            enshrineResponse.setTotalEnshrine(enshrineDao.countEnshrineByScenicSpotId(scenicSpotId));
            list.add(enshrineResponse);
        }
        PageInfo<EnshrineResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    public EnshrineResponse changeEnshrineToEnshrineResponse(Enshrine enshrine){
        EnshrineResponse enshrineResponse = new EnshrineResponse();

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(enshrine.getScenicSpotId());
        enshrineResponse.setScenicSpotName(scenicSpot.getScenicSpotName());
        enshrineResponse.setScenicSpotImg(scenicSpot.getScenicSpotImg());
        enshrineResponse.setScenicSpotDescribe(scenicSpot.getScenicSpotDescribe());

        User user = userDao.selectUserById(enshrine.getUserId());
        enshrineResponse.setNickName(user.getNickName());

        enshrineResponse.setCreateTime(enshrine.getCreateTime());
        return enshrineResponse;
    }
}
