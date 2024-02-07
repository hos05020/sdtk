package com.ktds.sport.debate.service;

import com.ktds.sport.debate.common.Id;
import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.CommentRequest;
import com.ktds.sport.debate.repository.CommentRepository;
import com.ktds.sport.debate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public void write(Id<Post, Long> postId, CommentRequest commentRequest) {
        Post post = postRepository.findById(postId.value()).orElseThrow(RuntimeException::new);
        Comment parent = null;
        if (commentRequest.getCommentId() != null) {
            parent = commentRepository.findById(commentRequest.getCommentId()).orElseThrow(() -> new RuntimeException());
        }
        Comment comment = commentRequest.newComment(post, parent);
        commentRepository.save(comment);
    }


    public List<Comment> findAll(Id<Post, Long> postId) {
        Post post = postRepository.findById(postId.value()).orElseThrow(RuntimeException::new);
        List<Comment> comments = commentRepository.findByPost(postId.value());
        return  buildCommentTree(comments);
    }

    private List<Comment> buildCommentTree(List<Comment> comments) {
        Map<Long, Comment> commentMap = new HashMap<>();

        // 댓글들을 Map에 추가하고, 부모-자식 관계를 설정합니다.
        for (Comment comment : comments) {
            commentMap.put(comment.getSeq(), comment);
            Comment parentComment = comment.getParent();
            if (parentComment != null) {
                parentComment.addChildComment(comment);
            }
        }

        // 최상위 댓글을 찾아 반환합니다.
        List<Comment> rootComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParent() == null) {
                rootComments.add(comment);
            }
        }

        return rootComments;
    }
}
