package com.codesmell.domain.entity;

import com.codesmell.domain.eum.RelationType;
import com.codesmell.domain.eum.TransportationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation")
    private TransportationType transportation;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation")
    private RelationType relation;

    @Column(name = "wish")
    private String wish;

}
