package com.codesmell.repository;

import com.codesmell.domain.entity.Comment;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommentRepository implements PanacheRepositoryBase<Comment, Long> {
}
