package com.ktds.sport.debate.controller;

import com.ktds.sport.debate.common.Id;
import com.ktds.sport.debate.domain.Comment;
import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.CommentRequest;
import com.ktds.sport.debate.dto.PageResponse;
import com.ktds.sport.debate.dto.PostDTO;
import com.ktds.sport.debate.dto.PostDeleteDTO;
import com.ktds.sport.debate.dto.PostSearchRequest;
import com.ktds.sport.debate.dto.PostingRequest;
import com.ktds.sport.debate.service.CommentService;
import com.ktds.sport.debate.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public String posting(@RequestBody PostingRequest request) {
        postService.write(request.newPost());
        return  "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable(name = "id") Long id,Model model) {
        PostDTO postDTO = postService.findById(Id.of(Post.class, id)).map(PostDTO::new).orElseThrow(() -> new RuntimeException("잘못된 아이디입니다"));
        model.addAttribute("post", postDTO);
        List<Comment> comments = commentService.findAll(Id.of(Post.class, id));
        model.addAttribute("comments", comments);
        return "post";
    }

    @GetMapping
    public String posts(@ModelAttribute PostSearchRequest postSearchRequest,Model model) {
        Page<Post> page = postService.search(postSearchRequest);
        model.addAttribute("posts",page.getContent());
        PageResponse result = PageResponse.of(page);
        System.out.println(result);
        model.addAttribute("result", result);
        return "index";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestBody PostDeleteDTO postDeleteDTO) {
        postService.remove(Id.of(Post.class, id), postDeleteDTO);
    }


}

