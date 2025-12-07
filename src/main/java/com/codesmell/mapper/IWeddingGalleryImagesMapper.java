package com.codesmell.mapper;

import com.codesmell.domain.dto.WeddingGalleryImagesDto;
import com.codesmell.domain.entity.WeddingGalleryImages;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface IWeddingGalleryImagesMapper extends IGenericMapper<WeddingGalleryImages, WeddingGalleryImagesDto> {
}
