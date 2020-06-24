package com.got.server.infrastructure;


import com.got.server.domain.Game;
import com.got.server.domain.Player;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {

    private final Map<String, Player> playerMap = new ConcurrentHashMap<>();


    @Override
    public Player findPlayer(final String id) {
        return playerMap.get(id);
    }

    @Override
    public Player save(final Player player) {
        return playerMap.put(player.getId(), player);
    }
}
