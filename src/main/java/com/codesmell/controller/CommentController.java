package com.codesmell.controller;

import com.codesmell.domain.dto.CommentDto;
import com.codesmell.domain.entity.Comment;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/comments")
public class CommentController extends AGenericController<Comment, CommentDto, Long> {

    public CommentController(IGenericService<Comment, CommentDto, Long> genericService) {
        super(genericService);
    }

}
