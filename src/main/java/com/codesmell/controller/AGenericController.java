package com.codesmell.controller;

import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;
import com.codesmell.service.IGenericService;
import com.codesmell.domain.api.R;
import com.codesmell.domain.api.PageRequest;
import com.codesmell.domain.api.RPage;
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
    public R<?> get(@PathParam("id") ID id) {
        Optional<D> dtoOptional = genericService.get(id);
        return R.ok(dtoOptional);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R<?> list(@BeanParam PageRequest pageParam) {
        RPage<D> page = genericService.list(pageParam);
        return R.ok(page);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R<?> create(D dto) {
        return R.ok(genericService.save(dto));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R<?> update(D dto) {
        return R.ok(genericService.save(dto));
    }

}
