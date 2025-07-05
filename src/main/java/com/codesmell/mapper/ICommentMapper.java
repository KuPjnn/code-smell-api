package com.codesmell.mapper;

import com.codesmell.domain.dto.CommentDto;
import com.codesmell.domain.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ICommentMapper extends IGenericMapper<Comment, CommentDto> {
}
