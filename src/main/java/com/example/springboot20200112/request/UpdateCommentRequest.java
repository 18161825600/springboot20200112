package com.example.springboot20200112.request;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private Long id;

    private String comment;
}
