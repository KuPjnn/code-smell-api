package com.codesmell.service;

import com.codesmell.domain.api.PageRequest;
import com.codesmell.domain.api.RPage;
import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface IGenericService<
        E extends BaseEntity<ID>,
        D extends BaseDto<ID>,
        ID> {

    Optional<D> get(ID id);

    D save(D dto);

    Collection<D> saveAll(Collection<D> dtos);

    RPage<D> list(PageRequest pageParam);

    void delete(Collection<ID> ids);
}
