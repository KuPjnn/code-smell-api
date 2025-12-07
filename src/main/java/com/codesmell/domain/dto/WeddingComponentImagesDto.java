package com.codesmell.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@RegisterForReflection
public class WeddingComponentImagesDto extends BaseDto<String> {

    private String title;

    private String url;

    private Integer orders;

}
