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
public class WeddingInviteDto extends BaseDto<Long> {

    private String fullName;

    private Boolean isInvite;

    private String phone;

    private Integer attendeesNo;

    private String wish;

}
