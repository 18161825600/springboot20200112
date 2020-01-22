package com.example.springboot20200112.controller;

import com.example.springboot20200112.request.AddCommentRequest;
import com.example.springboot20200112.request.UpdateCommentRequest;
import com.example.springboot20200112.response.CommentResponse;
import com.example.springboot20200112.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    //添加评论
    @PostMapping(value = "insert/comment")
    public Integer insertComment(@RequestBody AddCommentRequest addCommentRequest){
        return commentService.insertComment(addCommentRequest);
    }

    //删除评论
    @DeleteMapping(value = "delete/comment")
    public Integer deleteComment(@RequestBody List<Long> ids){
        return commentService.deleteComment(ids);
    }

    //修改评论
    @PutMapping(value = "update/comment")
    public Integer updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){
        return commentService.updateComment(updateCommentRequest);
    }

    //查看所有评论
    @GetMapping(value = "select/all/comment")
    public List<CommentResponse> selectAllComment(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return commentService.selectAllComment(pageNum);
    }

    //根据主键查看某一条评论
    @GetMapping(value = "select/one/comment")
    public CommentResponse selectOneComment(Long id){
        return commentService.selectOneComment(id);
    }

    //查看某个用户的所有评论
    @GetMapping(value = "select/comment/by/userId")
    public List<CommentResponse> selectCommentByUserId(Long userId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return commentService.selectCommentByUserId(userId, pageNum);
    }

    //查看某个景点的所有评论
    @GetMapping(value = "select/comment/by/scenicSpotId")
    public List<CommentResponse> selectCommentByScenicSpotId(Long scenicSpotId,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        return commentService.selectCommentByScenicSpotId(scenicSpotId, pageNum);
    }
}
