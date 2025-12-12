package com.codesmell.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "ntfy")
public interface NtfyProperties {
    String server();

    String topic();

    String username();

    String password();
}