package com.codesmell.controller;

import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingInvite;
import com.codesmell.service.IGenericService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/wedding/invites")
public class WeddingInviteController extends AGenericController<WeddingInvite, WeddingInviteDto, Long> {

    public WeddingInviteController(IGenericService<WeddingInvite, WeddingInviteDto, Long> genericService) {
        super(genericService);
    }

}
