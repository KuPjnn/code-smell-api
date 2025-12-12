package com.codesmell.service;

import com.codesmell.domain.dto.NtfyMessage;

public interface INtfyService {

    void sendNotificationAsync(NtfyMessage msg);

    void sendNotification(NtfyMessage msg);

}