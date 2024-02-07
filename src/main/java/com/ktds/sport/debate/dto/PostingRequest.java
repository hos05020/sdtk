package com.ktds.sport.debate.dto;

import com.ktds.sport.debate.domain.Post;
import lombok.Getter;

@Getter
public class PostingRequest {
    private String title;
    private String subtitle;
    private String content;


    private String postWriter;
    private String postPassword;
    private String postImageUrl;

    public  Post newPost() {
        return new Post(title,subtitle, content, postWriter, postPassword,postImageUrl);
    }
}
