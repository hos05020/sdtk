package com.ktds.sport.debate.controller;

import com.ktds.sport.debate.common.Id;
import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.CommentRequest;
import com.ktds.sport.debate.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    public List<Comment> commenting(@PathVariable(name = "id") Long id, @RequestBody CommentRequest commentRequest) {
        commentService.write(Id.of(Post.class, id), commentRequest);
        return commentService.findAll(Id.of(Post.class, id));
    }

    @GetMapping("/{id}/comments")
    public List<Comment> comments(@PathVariable(name = "id") Long id) {
        return commentService.findAll(Id.of(Post.class, id));
    }
}
