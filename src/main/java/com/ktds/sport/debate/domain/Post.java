package com.ktds.sport.debate.domain;

import com.ktds.sport.debate.common.BaseEntity;
import com.ktds.sport.debate.dto.PostDeleteRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;
    private String subtitle;


    @Column(length = 2048)
    private String content;


    private String postWriter;
    private String postPassword;


    private int comments;

    private String postImageUrl;


    public Post(String title,String subtitle ,String content, String postWriter, String postPassword,String postImageUrl) {
        this(null, title,subtitle,content, postWriter, postPassword, 0,postImageUrl);
    }


    private Post(Long seq, String title, String subtitle, String content, String postWriter, String postPassword, int comments, String postImageUrl) {
        this.seq = seq;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.postWriter = postWriter;
        this.postPassword = postPassword;
        this.comments = comments;
        this.postImageUrl = postImageUrl;
    }

    public boolean check(PostDeleteRequest postDeleteRequest) {
        return this.postWriter.equals(postDeleteRequest.getPostWriter()) && this.postPassword.equals(postDeleteRequest.getPostPassword());
    }

    public void updateImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
