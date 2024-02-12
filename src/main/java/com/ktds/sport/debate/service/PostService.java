package com.ktds.sport.debate.service;

import com.ktds.sport.debate.common.Id;
import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.PostDeleteDTO;
import com.ktds.sport.debate.dto.PostSearchRequest;
import com.ktds.sport.debate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    static final int PAGE_SIZE = 5;

    public void write(Post post) {
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(Id<Post, Long> postId) {
        checkArgument(postId != null, "postId must be provided.");
        return postRepository.findById(postId.value());
    }

    @Transactional(readOnly = true)
    public Page<Post> search(PostSearchRequest postSearchRequest) {
        return postRepository.searchAll(postSearchRequest);

    }

    public void remove(Id<Post, Long> postId, PostDeleteDTO postDeleteDTO) {
        Post post = findById(postId).orElseThrow(RuntimeException::new);
        if (post.check(postDeleteDTO)){
            postRepository.delete(post);
        } else throw new RuntimeException();
    }
}
