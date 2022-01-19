package com.ssafy.dtod.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTechstack is a Querydsl query type for Techstack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTechstack extends EntityPathBase<Techstack> {

    private static final long serialVersionUID = -92818645L;

    public static final QTechstack techstack = new QTechstack("techstack");

    public final NumberPath<Integer> techCode = createNumber("techCode", Integer.class);

    public final StringPath techType = createString("techType");

    public final StringPath techValues = createString("techValues");

    public QTechstack(String variable) {
        super(Techstack.class, forVariable(variable));
    }

    public QTechstack(Path<? extends Techstack> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTechstack(PathMetadata metadata) {
        super(Techstack.class, metadata);
    }

}

