package com.codesmell.service.impl;

import com.codesmell.config.NtfyProperties;
import com.codesmell.domain.dto.NtfyMessage;
import com.codesmell.service.INtfyService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
@ApplicationScoped
public class NtfyServiceImpl implements INtfyService {

    @Inject
    NtfyProperties ntfyProperties;

    private final Vertx vertx;
    private final WebClient client;

    @Inject
    public NtfyServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.client = WebClient.create(vertx);
    }

    @Override
    public void sendNotificationAsync(NtfyMessage msg) {
        Uni.createFrom().item(() -> {
            sendNotification(msg);
            return null;
        }).subscribe().with(item -> {
        });
    }

    @Override
    public void sendNotification(NtfyMessage msg) {

        JsonObject body = new JsonObject()
                .put("topic", ntfyProperties.topic())
                .put("markdown", true)
                .put("title", msg.getTitle())
                .put("message", msg.getMessage())
                .put("priority", msg.getPriority())
                .put("tags", msg.getTags());

        HttpRequest<?> request = client.postAbs(ntfyProperties.server());

        if (ntfyProperties.username().isPresent()
                && ntfyProperties.password().isPresent()) {
            String basicAuth = Base64.getEncoder()
                    .encodeToString((ntfyProperties.username().get() + ":" + ntfyProperties.password().get())
                            .getBytes());
            request.putHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
        }

        request.sendBuffer(body.toBuffer())
                .onSuccess(res -> {
                    if (res.statusCode() >= 200 && res.statusCode() < 300) {
                        log.info("Notification sent OK");
                    } else {
                        log.error("Failed: {} - {}", res.statusCode(), res.bodyAsString());
                    }
                })
                .onFailure(err -> log.error("Error sending notification", err));
    }
}
