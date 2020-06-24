package com.got.server.infrastructure;


import com.got.server.domain.Game;
import com.got.server.domain.Player;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepository implements GameRepository {

    private final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    @Override
    public Game findGame(final String id) {
        return gameMap.get(id);
    }

    @Override
    public Game getGame() {
        return gameMap.values().stream()
                .findFirst().orElseGet(() -> new Game(Boolean.FALSE));
    }

    @Override
    public Game endGame(final String id, final Player winner) {
        final Game game = gameMap.get(id);
        game.endGame(winner);
        return game;
    }

    @Override
    public Game saveGame(final Game game) {
        gameMap.put(game.getId(), game);
        return game;
    }

}
