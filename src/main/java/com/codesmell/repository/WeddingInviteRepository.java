package com.codesmell.repository;

import com.codesmell.domain.entity.WeddingInvite;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeddingInviteRepository implements PanacheRepositoryBase<WeddingInvite, Long> {
}
