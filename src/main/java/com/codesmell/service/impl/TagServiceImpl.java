package com.codesmell.service.impl;

import com.codesmell.domain.dto.TagDto;
import com.codesmell.domain.entity.Tag;
import com.codesmell.mapper.ITagMapper;
import com.codesmell.repository.TagRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class TagServiceImpl extends AGenericService<Tag, TagDto, Long> {

    @Inject
    public TagServiceImpl(TagRepository repository, ITagMapper mapper) {
        super(repository, mapper);
    }

}
