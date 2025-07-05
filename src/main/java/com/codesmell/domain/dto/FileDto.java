package com.codesmell.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class FileDto {

    private String fileName;

    private String filePath;

    private String fileType;

    private Boolean isLeaf;

    private Long size;

    private ZonedDateTime lastModified;

}
