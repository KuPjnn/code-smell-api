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
import lombok.NoArgsConstructor;

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
    public WeddingInviteDto submit(WeddingInviteDto dto) {
        WeddingInvite invite = weddingInviteMapper.toEntity(dto);
        invite = weddingInviteRepository.getEntityManager().merge(invite);

        String msg = "Thông báo mới: " + invite.getFullName() + " đã đăng ký tham gia tiệc cưới";
        ntfyService.sendNotificationAsync(new NtfyMessage(
                "Thông báo xác nhận tham dự cưới",
                msg,
                "high",
                "wedding"
        ));
        return mapper.toDto(invite);
    }
}
