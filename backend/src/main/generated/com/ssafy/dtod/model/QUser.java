package com.ssafy.dtod.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -548233550L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> userGrade = createNumber("userGrade", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPw = createString("userPw");

    public final NumberPath<Integer> userTechstack = createNumber("userTechstack", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

