package com.codesmell.controller;

import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingInvite;
import com.codesmell.service.IGenericService;
import com.codesmell.service.IWeddingInviteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/wedding/invites")
public class WeddingInviteController extends AGenericController<WeddingInvite, WeddingInviteDto, Long> {

    public WeddingInviteController(IGenericService<WeddingInvite, WeddingInviteDto, Long> genericService) {
        super(genericService);
    }

    @Inject
    IWeddingInviteService weddingInviteService;

    @POST
    @Path("/submit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WeddingInviteDto submit(WeddingInviteDto dto) {
        log.info("Submitting wedding invite: {}", dto);
        return weddingInviteService.submit(dto);
    }
}
