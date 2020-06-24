package com.got.client.service;

import com.got.client.domain.Player;
import com.got.client.event.PlayerJoined;
import com.got.client.event.PlayerMoved;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private final Player player;
    private final GameEventHandler gameEventHandler;

    public PlayerServiceImpl(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
        this.player = new Player(UUID.randomUUID().toString());
    }

    @Override
    public void play(PlayerMoved playerMoved) {
        final int nextNumber = player.play(playerMoved.getNumber());
        gameEventHandler.playerMoved(new PlayerMoved(player.getPlayerId(), playerMoved.getGameId(), nextNumber));
    }

    @Override
    public void joinGame() {
        log.info("Player: {} join the Game ", player.getPlayerId());
        gameEventHandler.playerJoined(new PlayerJoined(player.getPlayerId()));
    }
}
