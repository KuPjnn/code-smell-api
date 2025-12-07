package com.codesmell.mapper;

import com.codesmell.domain.dto.WeddingComponentImagesDto;
import com.codesmell.domain.entity.WeddingComponentImages;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface IWeddingComponentImagesMapper extends IGenericMapper<WeddingComponentImages, WeddingComponentImagesDto> {
}
