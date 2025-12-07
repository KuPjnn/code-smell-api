package com.codesmell.repository;

import com.codesmell.domain.entity.WeddingGalleryImages;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeddingGalleryImagesRepository implements PanacheRepositoryBase<WeddingGalleryImages, Long> {
}
