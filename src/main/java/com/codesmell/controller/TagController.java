package com.codesmell.controller;

import com.codesmell.domain.dto.TagDto;
import com.codesmell.domain.entity.Tag;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/tags")
public class TagController extends AGenericController<Tag, TagDto, Long> {

    public TagController(IGenericService<Tag, TagDto, Long> genericService) {
        super(genericService);
    }

}
