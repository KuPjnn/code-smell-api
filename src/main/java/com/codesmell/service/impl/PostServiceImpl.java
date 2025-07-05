package com.codesmell.service.impl;

import com.codesmell.domain.dto.PostDto;
import com.codesmell.domain.entity.Post;
import com.codesmell.mapper.IPostMapper;
import com.codesmell.repository.PostRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class PostServiceImpl extends AGenericService<Post, PostDto, Long> {

    @Inject
    public PostServiceImpl(PostRepository repository, IPostMapper mapper) {
        super(repository, mapper);
    }

}
