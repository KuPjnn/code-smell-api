package com.codesmell.controller;

import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;
import com.codesmell.service.IGenericService;
import com.codesmell.domain.api.ApiResult;
import com.codesmell.domain.api.PageRequest;
import com.codesmell.domain.api.PageResponse;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public abstract class AGenericController<
        E extends BaseEntity<ID>,
        D extends BaseDto<ID>,
        ID>
        implements IGenericController<E, D, ID> {

    private final IGenericService<E, D, ID> genericService;

    public AGenericController(IGenericService<E, D, ID> genericService) {
        this.genericService = genericService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> get(@PathParam("id") ID id) {
        Optional<D> dtoOptional = genericService.get(id);
        return ApiResult.success(dtoOptional);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult<?> list(@BeanParam PageRequest pageParam) {
        PageResponse<D> page = genericService.list(pageParam);
        return ApiResult.success(page);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResult<?> create(D dto) {
        return ApiResult.success(genericService.save(dto));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResult<?> update(D dto) {
        return ApiResult.success(genericService.save(dto));
    }

}
