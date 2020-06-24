package com.got.server.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.got.server.domain.GameStatus.CREATED;
import static com.got.server.domain.GameStatus.ENDED;
import static com.got.server.domain.GameStatus.STARTED;

@Getter
public class Game {

    private static final Integer MAX_ALLOWED_PLAYER_SIZE = 2;

    private final String id;
    private final List<Player> players;
    private final boolean isManual;
    private Player winner;
    private GameStatus gameStatus;

    public Game(boolean isManual) {
        this.id = UUID.randomUUID().toString();
        this.gameStatus = CREATED;
        this.isManual = isManual;
        this.players = new ArrayList<>();
    }

    public boolean addPlayer(final Player player) {
        if (players.size() >= MAX_ALLOWED_PLAYER_SIZE) {
            throw new IllegalStateException("Max Allowed Player Size Reached!");
        }
        return players.add(player);
    }

    public void startGame() {
        this.gameStatus = STARTED;
    }

    public void endGame(final Player winner) {
        this.gameStatus = ENDED;
        this.winner = winner;
    }
}
