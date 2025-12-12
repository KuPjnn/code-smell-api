package com.codesmell.domain.dto;

import com.codesmell.domain.eum.RelationType;
import com.codesmell.domain.eum.TransportationType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@RegisterForReflection
public class WeddingInviteDto extends BaseDto<Long> {

    private String fullName;

    private Boolean isInvite;

    private String phone;

    private Integer attendeesNo;

    private TransportationType transportation;

    private RelationType relation;

    private String wish;

    public String toNtfyMessage() {
        if (isInvite) {
            return "Có một khách mời sẽ tham dự cưới." + "\n" +
                    "Họ và tên: " + fullName + "\n" +
                    "Số điện thoại: " + phone + "\n" +
                    "Số người tham dự: " + attendeesNo + "\n" +
                    "Phương tiện: " + transportation.getValue() + "\n" +
                    "Quan hệ: " + relation.getValue() + "\n" +
                    "Lời chúc: " + wish;
        } else {
            return "Có một khách mời không tham dự cưới." + "\n" +
                    "Họ và tên: " + fullName + "\n" +
                    "Lời chúc: " + wish;
        }
    }
}
