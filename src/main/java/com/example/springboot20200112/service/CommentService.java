package com.example.springboot20200112.service;

import com.example.springboot20200112.pojo.Comment;
import com.example.springboot20200112.request.AddCommentRequest;
import com.example.springboot20200112.request.UpdateCommentRequest;
import com.example.springboot20200112.response.CommentResponse;

import java.util.List;

public interface CommentService {

    Integer insertComment(AddCommentRequest addCommentRequest);

    Integer deleteComment(List<Long> ids);

    Integer updateComment(UpdateCommentRequest updateCommentRequest);

    List<CommentResponse> selectAllComment(Integer pageNum);

    CommentResponse selectOneComment(Long id);

    List<CommentResponse> selectCommentByUserId(Long userId,Integer pageNum);

    List<CommentResponse> selectCommentByScenicSpotId(Long scenicSpotId,Integer pageNum);
}
