package com.ktds.sport.debate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktds.sport.debate.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;


    private String commentWriter;
    private String commentPassword;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    private void updateParent(Comment comment) {
        this.parent = comment;
    }

    public Comment(String commentWriter, String commentPassword, String content, Post post, Comment parent) {
        this(null, commentWriter, commentPassword, content, post, parent);
    }


    public Comment(Long seq, String commentWriter, String commentPassword, String content, Post post, Comment parent) {
        this.seq = seq;
        this.commentWriter = commentWriter;
        this.commentPassword = commentPassword;
        this.content = content;
        this.post = post;
        this.parent = parent;
    }

    public void addChildComment(Comment comment) {
        this.children.add(comment);
    }


    public Long getSeq() {
        return seq;
    }

    public String getContent() {
        return content;
    }

    public String getCommentWriter() {
        return commentWriter;
    }

    public Comment getParent() {
        return parent;
    }

    public List<Comment> getChildren() {
        return children;
    }
}
