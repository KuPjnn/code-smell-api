package com.codesmell.service.impl;

import com.codesmell.domain.dto.WeddingGalleryImagesDto;
import com.codesmell.domain.entity.WeddingGalleryImages;
import com.codesmell.mapper.IWeddingGalleryImagesMapper;
import com.codesmell.repository.WeddingGalleryImagesRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class WeddingGalleryImagesServiceImpl extends AGenericService<WeddingGalleryImages, WeddingGalleryImagesDto, Long> {

    @Inject
    public WeddingGalleryImagesServiceImpl(WeddingGalleryImagesRepository repository, IWeddingGalleryImagesMapper mapper) {
        super(repository, mapper);
    }

}
