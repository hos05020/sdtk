package com.ktds.sport.debate.dto;

import com.ktds.sport.debate.domain.Post;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class PostDTO {

    private Long seq;

    private String title;
    private String subtitle;
    private String content;


    private String postWriter;


    private int comments;

    private String postImageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public PostDTO(Post post) {
        this.seq = post.getSeq();
        this.title = post.getTitle();
        this.subtitle = post.getSubtitle();
        this.content = post.getContent();
        this.postWriter = post.getPostWriter();
        this.comments = post.getComments();
        this.postImageUrl = post.getPostImageUrl();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

}
