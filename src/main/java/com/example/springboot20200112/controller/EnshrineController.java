package com.example.springboot20200112.controller;

import com.example.springboot20200112.request.AddEnshrineRequest;
import com.example.springboot20200112.response.EnshrineResponse;
import com.example.springboot20200112.service.EnshrineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnshrineController {

    @Autowired
    private EnshrineService enshrineService;

    //添加收藏记录
    @PostMapping(value = "insert/enshrine")
    public Integer insertEnshrine(@RequestBody AddEnshrineRequest addEnshrineRequest){
        return enshrineService.insertEnshrine(addEnshrineRequest);
    }

    //删除收藏记录
    @DeleteMapping(value = "delete/enshrine")
    public Integer deleteEnshrine(@RequestBody List<Long> ids){
        return enshrineService.deleteEnshrine(ids);
    }

    //查找所有的收藏记录
    @GetMapping(value = "select/all/enshrine")
    public PageInfo<EnshrineResponse> selectAllEnshrine(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        return enshrineService.selectAllEnshrine(pageNum);
    }

    //查找某个用户的所有收藏记录
    @GetMapping(value = "select/enshrine/by/userId")
    public List<EnshrineResponse> selectEnshrineByUserId(Long userId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return enshrineService.selectEnshrineByUserId(userId, pageNum);
    }

    //查找某个景点被哪些用户收藏了
    @GetMapping(value = "select/enshrine/by/scenicSpotId")
    public List<EnshrineResponse> selectEnshrineByScenicSpotId( Long scenicSpotId, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return enshrineService.selectEnshrineByScenicSpotId(scenicSpotId, pageNum);
    }
}
