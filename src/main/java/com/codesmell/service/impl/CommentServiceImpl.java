package com.codesmell.service.impl;

import com.codesmell.domain.dto.CommentDto;
import com.codesmell.domain.entity.Comment;
import com.codesmell.mapper.ICommentMapper;
import com.codesmell.repository.CommentRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class CommentServiceImpl extends AGenericService<Comment, CommentDto, Long> {

    @Inject
    public CommentServiceImpl(CommentRepository repository, ICommentMapper mapper) {
        super(repository, mapper);
    }

}
