package com.codesmell.controller;

import com.codesmell.domain.dto.CategoryDto;
import com.codesmell.domain.entity.Category;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/categories")
public class CategoryController extends AGenericController<Category, CategoryDto, Long> {

    public CategoryController(IGenericService<Category, CategoryDto, Long> service) {
        super(service);
    }

}
