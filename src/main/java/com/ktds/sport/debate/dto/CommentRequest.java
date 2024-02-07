package com.ktds.sport.debate.dto;

import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.domain.Post;
import lombok.Getter;

@Getter
public class CommentRequest {



    private String commentWriter;
    private String commentPassword;

    private String content;
    private Long commentId;


    public Comment newComment(Post post, Comment parent) {
        return new Comment(commentWriter, commentPassword, content, post, parent);
    }
}
