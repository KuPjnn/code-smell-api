package com.codesmell.repository;

import com.codesmell.domain.entity.Post;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepositoryBase<Post, Long> {
}
