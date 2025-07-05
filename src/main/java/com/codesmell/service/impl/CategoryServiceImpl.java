package com.codesmell.service.impl;


import com.codesmell.domain.dto.CategoryDto;
import com.codesmell.domain.entity.Category;
import com.codesmell.mapper.ICategoryMapper;
import com.codesmell.repository.CategoryRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class CategoryServiceImpl extends AGenericService<Category, CategoryDto, Long> {

    @Inject
    public CategoryServiceImpl(CategoryRepository repository, ICategoryMapper mapper) {
        super(repository, mapper);
    }

}
