package com.codesmell.service.impl;

import com.codesmell.config.NtfyProperties;
import com.codesmell.domain.dto.NtfyMessage;
import com.codesmell.service.INtfyService;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
@ApplicationScoped
public class NtfyServiceImpl implements INtfyService {

    @Inject
    NtfyProperties ntfyProperties;

    @Inject
    WebClient client;

    @Override
    public void sendNotificationAsync(NtfyMessage msg) {
        Uni.createFrom().item(() -> {
            sendNotification(msg);
            return Uni.createFrom().voidItem();
        });
    }

    @Override
    public void sendNotification(NtfyMessage msg) {

        JsonObject body = new JsonObject()
                .put("title", msg.getTitle())
                .put("message", msg.getMessage())
                .put("priority", msg.getPriority())
                .put("tags", msg.getTags());

        HttpRequest<?> request = client
                .postAbs(ntfyProperties.server() + "/" + ntfyProperties.topic())
                .putHeader("Content-Type", "application/json");

        if (!ntfyProperties.username().isEmpty()
                && !ntfyProperties.password().isEmpty()) {
            String basicAuth = Base64.getEncoder()
                    .encodeToString((ntfyProperties.username() + ":" + ntfyProperties.password())
                            .getBytes());
            request.putHeader("Authorization", "Basic " + basicAuth);
        }

        request.sendBuffer(body.toBuffer())
                .onSuccess(res -> {
                    if (res.statusCode() >= 200 && res.statusCode() < 300) {
                        log.info("Notification sent OK");
                    } else {
                        log.error("Failed: {} {}", res.statusCode(), res.bodyAsString());
                    }
                })
                .onFailure(err -> log.error("Error sending notification", err));
    }
}
