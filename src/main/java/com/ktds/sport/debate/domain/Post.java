package com.ktds.sport.debate.domain;

import com.ktds.sport.debate.common.BaseEntity;
import com.ktds.sport.debate.dto.PostDeleteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {


    @Id
    @GeneratedValue
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

    public boolean check(PostDeleteDTO postDeleteDTO) {
        return this.postWriter.equals(postDeleteDTO.getPostWriter()) && this.postPassword.equals(postDeleteDTO.getPostPassword());
    }

    public void updateImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
