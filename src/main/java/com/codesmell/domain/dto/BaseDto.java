package com.codesmell.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
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
@RegisterForReflection
public abstract class BaseDto<ID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private ID id;

    private Instant createdDate;

    private String createdBy;

    private Instant updatedDate;

    private String updatedBy;

}
