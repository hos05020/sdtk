package com.ktds.sport.debate.repository;

import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.PostSearchRequest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    Page<Post> searchAll(PostSearchRequest postSearchRequest);

    Long getTotalPage(PostSearchRequest postSearchRequest);
}
