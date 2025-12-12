package com.codesmell.domain.eum;

import lombok.Getter;

public enum RelationType {
    BRIDE_FRIEND("Bạn cô dâu"),
    GROOM_FRIEND("Bạn chú rể"),
    ;

    RelationType(String value) {
        this.value = value;
    }

    @Getter
    private final String value;
}
