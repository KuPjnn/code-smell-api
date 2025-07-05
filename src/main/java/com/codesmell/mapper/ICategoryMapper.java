package com.codesmell.mapper;

import com.codesmell.domain.dto.CategoryDto;
import com.codesmell.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ICategoryMapper extends IGenericMapper<Category, CategoryDto> {
}
