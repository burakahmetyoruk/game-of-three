package com.got.server.service;

import com.got.server.domain.Player;
import com.got.server.event.PlayerJoined;
import com.got.server.event.PlayerMoved;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GameEventListener {

    private final GameService gameService;

    @StreamListener("gameQueue")
    public void playerJoined(final PlayerJoined playerJoined) {
        log.info("Player Joined: {} ", playerJoined.getPlayerId());
        gameService.addPlayer(Player.from(playerJoined));
        gameService.start();
    }

    @StreamListener("playerListenerQueue")
    public void opponentMoved(final PlayerMoved playerMoved) {
        log.info("Player2: {} number: {} game {}", playerMoved.getPlayerId(),
                playerMoved.getNumber(),
                playerMoved.getGameId()
        );

        gameService.playerMove(playerMoved);

    }
}
