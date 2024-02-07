package com.ktds.sport.debate.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 2005977776L;

    public static final QPost post = new QPost("post");

    public final com.ktds.sport.debate.common.QBaseEntity _super = new com.ktds.sport.debate.common.QBaseEntity(this);

    public final NumberPath<Integer> comments = createNumber("comments", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath postImageUrl = createString("postImageUrl");

    public final StringPath postPassword = createString("postPassword");

    public final StringPath postWriter = createString("postWriter");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath subtitle = createString("subtitle");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

