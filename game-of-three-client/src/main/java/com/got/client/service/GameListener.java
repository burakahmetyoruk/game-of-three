package com.got.client.service;

import com.got.client.event.GameStatusChanged;
import com.got.client.event.PlayerMoved;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameListener {

    private final PlayerService playerService;

    @StreamListener("playerListenerQueue")
    public void playerMoved(final PlayerMoved playerMoved) {
        log.info("Player1: {} - number: {} - game: {}", playerMoved.getPlayerId(),
                playerMoved.getNumber(), playerMoved.getGameId());

        playerService.play(playerMoved);
    }

    @StreamListener("clientQueue")
    public void gameStarted(final GameStatusChanged gameStatusChanged) {
        log.info("Game: {} Status: {}",gameStatusChanged.getGameId(), gameStatusChanged.getStatus());
    }
}
