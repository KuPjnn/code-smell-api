package com.codesmell.mapper;

import com.codesmell.domain.dto.TagDto;
import com.codesmell.domain.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ITagMapper extends IGenericMapper<Tag, TagDto> {
}
