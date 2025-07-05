package com.codesmell.mapper;

import com.codesmell.domain.dto.PostDto;
import com.codesmell.domain.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface IPostMapper extends IGenericMapper<Post, PostDto> {
}
