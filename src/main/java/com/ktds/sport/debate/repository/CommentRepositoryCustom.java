package com.ktds.sport.debate.repository;

import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.dto.CommentDTO;

import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findByPost(Long postId);
}
