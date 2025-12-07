package com.codesmell.mapper;

import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingInvite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface IWeddingInviteMapper extends IGenericMapper<WeddingInvite, WeddingInviteDto> {
}
