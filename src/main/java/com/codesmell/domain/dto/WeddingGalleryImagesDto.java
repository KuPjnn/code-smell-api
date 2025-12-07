package com.codesmell.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class WeddingGalleryImagesDto extends BaseDto<Long> {

    private String title;

    private String url;

    private Integer orders;

}
