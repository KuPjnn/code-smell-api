package com.codesmell.controller;

import com.codesmell.domain.api.R;
import com.codesmell.domain.dto.WeddingGalleryImagesDto;
import com.codesmell.domain.entity.WeddingGalleryImages;
import com.codesmell.service.IGenericService;
import com.codesmell.service.impl.WeddingGalleryImagesServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Path("/api/wedding/gallery/images")
public class WeddingGalleryImagesController extends AGenericController<WeddingGalleryImages, WeddingGalleryImagesDto, Long> {

    public WeddingGalleryImagesController(IGenericService<WeddingGalleryImages, WeddingGalleryImagesDto, Long> genericService) {
        super(genericService);
    }

    @Inject
    WeddingGalleryImagesServiceImpl weddingGalleryImagesService;

    @POST
    @Path("/saveAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R<?> saveAll(List<WeddingGalleryImagesDto> dtos) {
        return R.ok(weddingGalleryImagesService.saveAll(dtos));
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R<?> delete(List<Long> ids) {
        weddingGalleryImagesService.delete(ids);
        return R.ok();
    }

}
