package com.codesmell.domain.eum;

import lombok.Getter;

public enum TransportationType {
    SELF_ARRANGED("Tự túc"),
    SHUTTLE_SERVICE("Xe đưa đón"),
    ;

    TransportationType(String value) {
        this.value = value;
    }

    @Getter
    private final String value;
}
