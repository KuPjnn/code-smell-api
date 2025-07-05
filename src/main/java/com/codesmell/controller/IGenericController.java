package com.codesmell.controller;

import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;
import com.codesmell.domain.api.ApiResult;
import com.codesmell.domain.api.PageRequest;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.PathParam;

public interface IGenericController<
        E extends BaseEntity<ID>,
        D extends BaseDto<ID>,
        ID> {

    ApiResult<?> get(@PathParam("id") ID id);

    ApiResult<?> list(@BeanParam PageRequest pageParam);

    ApiResult<?> create(D dto);

    ApiResult<?> update(D dto);

}
