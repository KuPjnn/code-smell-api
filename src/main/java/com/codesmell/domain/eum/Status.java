package com.codesmell.domain.eum;

public enum Status {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    ARCHIVED("ARCHIVED"),
    ;

    final String value;

    Status(String value) {
        this.value = value;
    }
}
