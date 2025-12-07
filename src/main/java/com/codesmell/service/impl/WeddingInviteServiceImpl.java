package com.codesmell.service.impl;

import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingInvite;
import com.codesmell.mapper.IWeddingInviteMapper;
import com.codesmell.repository.WeddingInviteRepository;
import com.codesmell.service.AGenericService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApplicationScoped
public class WeddingInviteServiceImpl extends AGenericService<WeddingInvite, WeddingInviteDto, Long> {

    @Inject
    public WeddingInviteServiceImpl(WeddingInviteRepository repository, IWeddingInviteMapper mapper) {
        super(repository, mapper);
    }

}
