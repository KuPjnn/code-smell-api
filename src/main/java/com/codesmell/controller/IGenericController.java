package com.codesmell.controller;

import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;
import com.codesmell.domain.api.R;
import com.codesmell.domain.api.PageRequest;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.PathParam;

public interface IGenericController<
        E extends BaseEntity<ID>,
        D extends BaseDto<ID>,
        ID> {

    R<?> get(@PathParam("id") ID id);

    R<?> list(@BeanParam PageRequest pageParam);

    R<?> create(D dto);

    R<?> update(D dto);

}
