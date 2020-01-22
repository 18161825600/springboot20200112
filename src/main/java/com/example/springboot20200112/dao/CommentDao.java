package com.example.springboot20200112.dao;

import com.example.springboot20200112.mapper.CommentMapper;
import com.example.springboot20200112.mapper.CommonMapper;
import com.example.springboot20200112.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class CommentDao {

    @Autowired
    private CommentMapper commentMapper;

    public Integer insertComment(Comment comment){
        return commentMapper.insert(comment);
    }

    public Integer deleteComment(List<Long> ids){
        Example example = new Example(Comment.class);
        example.createCriteria().andIn("id",ids);
        return commentMapper.deleteByPrimaryKey(example);
    }

    public Integer updateComment(Comment comment){
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    public List<Comment> selectAllComment(){
        return commentMapper.selectAll();
    }

    public Comment selectOneComment(Long id){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("id",id);
        return commentMapper.selectOneByExample(example);
    }

    public List<Comment> selectCommentByUserId(Long userId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("userId",userId);
        return commentMapper.selectByExample(example);
    }

    public List<Comment> selectCommentByScenicSpotId(Long scenicSpotId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return commentMapper.selectByExample(example);
    }

    public Integer countAllComment(){
        Example example = new Example(Comment.class);
        return commentMapper.selectCountByExample(example);
    }

    public Integer countCommentByUserId(Long userId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("userId",userId);
        return commentMapper.selectCountByExample(example);
    }

    public Integer countCommentByScenicSpotId(Long scenicSpotId){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("scenicSpotId",scenicSpotId);
        return commentMapper.selectCountByExample(example);
    }
}
