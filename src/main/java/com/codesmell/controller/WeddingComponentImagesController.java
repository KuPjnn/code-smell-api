package com.codesmell.controller;

import com.codesmell.domain.dto.WeddingComponentImagesDto;
import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingComponentImages;
import com.codesmell.domain.entity.WeddingInvite;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/wedding/component/images")
public class WeddingComponentImagesController extends AGenericController<WeddingComponentImages, WeddingComponentImagesDto, String> {

    public WeddingComponentImagesController(IGenericService<WeddingComponentImages, WeddingComponentImagesDto, String> genericService) {
        super(genericService);
    }

}
