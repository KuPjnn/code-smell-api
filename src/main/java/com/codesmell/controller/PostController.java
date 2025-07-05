package com.codesmell.controller;

import com.codesmell.domain.dto.PostDto;
import com.codesmell.domain.entity.Post;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/posts")
public class PostController extends AGenericController<Post, PostDto, Long> {

    public PostController(IGenericService<Post, PostDto, Long> genericService) {
        super(genericService);
    }

}
