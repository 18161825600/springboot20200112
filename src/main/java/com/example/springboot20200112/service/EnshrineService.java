package com.example.springboot20200112.service;

import com.example.springboot20200112.pojo.Enshrine;
import com.example.springboot20200112.request.AddEnshrineRequest;
import com.example.springboot20200112.response.EnshrineResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface EnshrineService {

    Integer insertEnshrine(AddEnshrineRequest addEnshrineRequest);

    Integer deleteEnshrine(List<Long> ids);

    PageInfo<EnshrineResponse> selectAllEnshrine(Integer pageNum);

    List<EnshrineResponse> selectEnshrineByUserId(Long userId,Integer pageNum);

    List<EnshrineResponse> selectEnshrineByScenicSpotId(Long scenicSpotId,Integer pageNum);
}
