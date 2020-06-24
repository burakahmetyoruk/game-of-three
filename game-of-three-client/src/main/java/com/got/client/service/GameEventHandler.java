package com.got.client.service;

import com.got.client.config.QueueProcessor;
import com.got.client.event.PlayerJoined;
import com.got.client.event.PlayerMoved;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameEventHandler {

    private final QueueProcessor queueProcessor;

    public void playerJoined(final PlayerJoined playerJoined) {
        queueProcessor.game().send(message(playerJoined));
    }

    public void playerMoved(final PlayerMoved playerMoved) {
        log.info("Player2: {} - Number: {} - game ID: {}", playerMoved.getPlayerId(),
                playerMoved.getNumber(), playerMoved.getGameId());
        queueProcessor.playerSender().send(message(playerMoved));
    }

    private <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
