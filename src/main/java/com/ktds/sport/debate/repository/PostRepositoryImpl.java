package com.ktds.sport.debate.repository;

import static com.ktds.sport.debate.domain.QPost.*;

import com.ktds.sport.debate.domain.Post;
import com.ktds.sport.debate.dto.PostSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Post> searchAll(PostSearchRequest postSearchRequest) {
        List<Post> posts = queryFactory.selectFrom(post)
            .where(searchQuery(postSearchRequest))
            .orderBy(post.seq.desc())
            .offset(postSearchRequest.getOffset())
            .limit(4)
            .fetch();

        return new PageImpl<>(posts, PageRequest.of(postSearchRequest.getPage()  - 1,4),getTotalPage(postSearchRequest));
    }


    @Override
    public Long getTotalPage(PostSearchRequest postSearchRequest) {
        return queryFactory.select(post.count())
            .where(searchQuery(postSearchRequest))
            .from(post)
            .fetchOne();
    }

    public BooleanBuilder searchQuery(PostSearchRequest postSearchRequest){

        String type = postSearchRequest.getType();
        String keyword = postSearchRequest.getKeyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(product.id.gt(0L));//이거 없으면 결과값이 없음

        if(type==null|| type.trim().isEmpty()){
            return booleanBuilder;
        }


        if(type.contains("tc")){
            booleanBuilder.or(post.title.contains(keyword)).or(post.content.contains(keyword));
        }
        else if(type.contains("t")){
            booleanBuilder.and(post.title.contains(keyword));
        }
        else if(type.contains("c")){
            booleanBuilder.and(post.content.contains(keyword));
        }else if (type.contains("w")){
            booleanBuilder.and(post.postWriter.contains(keyword));
        }

        return booleanBuilder;
    }
}
