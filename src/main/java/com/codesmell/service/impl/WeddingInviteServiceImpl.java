package com.codesmell.service.impl;

import com.codesmell.domain.dto.NtfyMessage;
import com.codesmell.domain.dto.WeddingInviteDto;
import com.codesmell.domain.entity.WeddingInvite;
import com.codesmell.mapper.IWeddingInviteMapper;
import com.codesmell.repository.WeddingInviteRepository;
import com.codesmell.service.AGenericService;
import com.codesmell.service.INtfyService;
import com.codesmell.service.IWeddingInviteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ApplicationScoped
public class WeddingInviteServiceImpl extends AGenericService<WeddingInvite, WeddingInviteDto, Long>
        implements IWeddingInviteService {

    @Inject
    public WeddingInviteServiceImpl(WeddingInviteRepository repository, IWeddingInviteMapper mapper) {
        super(repository, mapper);
    }

    @Inject
    INtfyService ntfyService;

    @Inject
    IWeddingInviteMapper weddingInviteMapper;

    @Inject
    WeddingInviteRepository weddingInviteRepository;

    @Override
    @Transactional
    public WeddingInviteDto submit(WeddingInviteDto dto) {
        WeddingInvite invite = weddingInviteMapper.toEntity(dto);
        invite = weddingInviteRepository.getEntityManager().merge(invite);

        // send notification
        String title = "Thông báo xác nhận tham dự cưới";
        String msg = dto.toNtfyMessage();
        List<String> tags = new ArrayList<>();
        tags.add("wedding");
        if (dto.getIsInvite()) {
            tags.add("white_check_mark");
        } else {
            tags.add("no_entry");
        }
        NtfyMessage ntfyMsg = new NtfyMessage(title, msg, 3, tags);
        ntfyService.sendNotificationAsync(ntfyMsg);

        return mapper.toDto(invite);
    }
}
