package com.codesmell.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDto<ID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ID id;

    private Instant createdDate;

    private String createdBy;

    private Instant updatedDate;

    private String updatedBy;

}
