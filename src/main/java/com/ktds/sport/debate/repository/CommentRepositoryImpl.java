package com.ktds.sport.debate.repository;

import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.domain.QComment;
import com.ktds.sport.debate.domain.QPost;
import com.ktds.sport.debate.dto.CommentDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ktds.sport.debate.domain.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private final QComment qComment = comment;
    private final QPost qPost = QPost.post;

    @Override
    public List<Comment> findByPost(Long postId) {
        return queryFactory.selectFrom(comment)
                .where(comment.post.seq.eq(postId))
                .orderBy(comment.createdAt.asc())
                .fetch();
    }

}
