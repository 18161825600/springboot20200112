package com.example.springboot20200112.service.impl;

import com.example.springboot20200112.dao.CommentDao;
import com.example.springboot20200112.dao.ScenicSpotDao;
import com.example.springboot20200112.dao.UserDao;
import com.example.springboot20200112.pojo.Comment;
import com.example.springboot20200112.pojo.ScenicSpot;
import com.example.springboot20200112.pojo.User;
import com.example.springboot20200112.request.AddCommentRequest;
import com.example.springboot20200112.request.UpdateCommentRequest;
import com.example.springboot20200112.response.CommentResponse;
import com.example.springboot20200112.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Override
    public Integer insertComment(AddCommentRequest addCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentRequest,comment);
        comment.setCreateTime(new Date());
        return commentDao.insertComment(comment);
    }

    @Override
    public Integer deleteComment(List<Long> ids) {
        return commentDao.deleteComment(ids);
    }

    @Override
    public Integer updateComment(UpdateCommentRequest updateCommentRequest) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(updateCommentRequest,comment);
        comment.setUpdateTime(new Date());
        return commentDao.updateComment(comment);
    }

    @Override
    public List<CommentResponse> selectAllComment(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Comment> comments = commentDao.selectAllComment();
        List<CommentResponse> list = new ArrayList<>();
        for (Comment comment: comments) {
            CommentResponse commentResponse = transformation(comment);
            commentResponse.setTotalComment(commentDao.countAllComment());

            list.add(commentResponse);
        }
        PageInfo<CommentResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public CommentResponse selectOneComment(Long id) {
        Comment comment = commentDao.selectOneComment(id);
        return transformation(comment);
    }

    @Override
    public List<CommentResponse> selectCommentByUserId(Long userId,Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Comment> comments = commentDao.selectCommentByUserId(userId);
        List<CommentResponse> list = new ArrayList<>();
        for (Comment comment:comments) {
            CommentResponse commentResponse = transformation(comment);
            commentResponse.setTotalComment(commentDao.countCommentByUserId(userId));
            list.add(commentResponse);
        }
        PageInfo<CommentResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<CommentResponse> selectCommentByScenicSpotId(Long scenicSpotId,Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Comment> comments = commentDao.selectCommentByScenicSpotId(scenicSpotId);
        List<CommentResponse> list = new ArrayList<>();
        for (Comment comment: comments) {
            CommentResponse commentResponse = transformation(comment);
            commentResponse.setTotalComment(commentDao.countCommentByScenicSpotId(scenicSpotId));
            list.add(commentResponse);
        }
        PageInfo<CommentResponse> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    //comment转换成commentResponse
    public CommentResponse transformation(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        User user = userDao.selectUserById(comment.getUserId());
        commentResponse.setNickName(user.getNickName());

        ScenicSpot scenicSpot = scenicSpotDao.selectScenicSpotById(comment.getScenicSpotId());
        commentResponse.setScenicSpotName(scenicSpot.getScenicSpotName());

        commentResponse.setComment(comment.getComment());
        commentResponse.setCreateTime(comment.getCreateTime());

        return commentResponse;
    }
}
