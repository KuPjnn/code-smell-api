package com.codesmell.config;

import io.smallrye.config.ConfigMapping;

import java.util.Optional;

@ConfigMapping(prefix = "ntfy")
public interface NtfyProperties {
    String server();

    String topic();

    Optional<String> username();

    Optional<String> password();
}