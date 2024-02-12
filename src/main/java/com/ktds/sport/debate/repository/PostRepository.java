package com.ktds.sport.debate.repository;

import com.ktds.sport.debate.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>,PostRepositoryCustom {
}
