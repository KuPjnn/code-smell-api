package com.codesmell.repository;

import com.codesmell.domain.entity.WeddingComponentImages;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeddingComponentImagesRepository implements PanacheRepositoryBase<WeddingComponentImages, String> {
}
