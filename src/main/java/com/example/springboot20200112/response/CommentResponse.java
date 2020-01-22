package com.example.springboot20200112.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {

    private String nickName;

    private String scenicSpotName;

    private String comment;

    private Date createTime;

    private Integer totalComment;
}
