package com.codesmell.util;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdGenerator {

    private final Snowflake snowflake = new Snowflake(1, 1);

    public String nextId() {
        return String.valueOf(snowflake.generateId());
    }
}