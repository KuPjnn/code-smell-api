package com.codesmell.service.impl;

import com.codesmell.domain.dto.WeddingComponentImagesDto;
import com.codesmell.domain.entity.WeddingComponentImages;
import com.codesmell.mapper.IWeddingComponentImagesMapper;
import com.codesmell.repository.WeddingComponentImagesRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class WeddingComponentImagesServiceImpl extends AGenericService<WeddingComponentImages, WeddingComponentImagesDto, String> {

    @Inject
    public WeddingComponentImagesServiceImpl(WeddingComponentImagesRepository repository, IWeddingComponentImagesMapper mapper) {
        super(repository, mapper);
    }

}
