package com.ktds.sport.debate.dto;

import com.ktds.sport.debate.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDTO {
    private Long seq;


    private String commentWriter;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private List<CommentDTO> children = new ArrayList<>();

    public CommentDTO(Long seq, String commentWriter, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.seq = seq;
        this.commentWriter = commentWriter;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentDTO convert(Comment comment) {
        return new CommentDTO(comment.getSeq(), comment.getCommentWriter(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }
}
