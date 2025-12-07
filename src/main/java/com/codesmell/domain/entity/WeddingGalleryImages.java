package com.codesmell.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "cms_wedding_gallery_images")
public class WeddingGalleryImages extends BaseEntity<Long> {

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "url")
    private String url;

    @NotNull
    @Column(name = "orders")
    private Integer orders;

}
