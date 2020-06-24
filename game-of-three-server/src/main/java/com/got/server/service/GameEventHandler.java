package com.got.server.service;

import com.got.server.config.QueueProcessor;
import com.got.server.event.GameEnded;
import com.got.server.event.GameStarted;
import com.got.server.event.PlayerMoved;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GameEventHandler {

    private final QueueProcessor queueProcessor;

    public void playerMoved(PlayerMoved playerMoved) {
        log.info("Player1: {} - Number: {} - game ID: {}", playerMoved.getPlayerId(),
                playerMoved.getNumber(), playerMoved.getGameId());
        queueProcessor.playerSender().send(message(playerMoved));
    }

    public void gameStarted(final GameStarted gameStarted) {
        log.info("Game Started: {} ", gameStarted.getGameId());
        queueProcessor.client().send(message(gameStarted));
    }

    public void gameEnded(final GameEnded gameEnded) {
        log.info("Game Ended: {}  Winner: {}", gameEnded.getGameId(), gameEnded.getWinner());
        queueProcessor.client().send(message(gameEnded));
    }

    private <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}