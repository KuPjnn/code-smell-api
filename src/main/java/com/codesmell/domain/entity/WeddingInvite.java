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
@Table(name = "cms_wedding_invite")
public class WeddingInvite extends BaseEntity<Long> {

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "is_invite")
    private Boolean isInvite;

    @Column(name = "phone")
    private String phone;

    @Column(name = "attendees_no")
    private Integer attendeesNo;

    @Column(name = "wish")
    private String wish;

}
