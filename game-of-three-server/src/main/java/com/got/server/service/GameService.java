package com.got.server.service;

import com.got.server.domain.Game;
import com.got.server.domain.Player;
import com.got.server.event.PlayerMoved;

public interface GameService {

    Game create();
    boolean addPlayer(Player player);
    void start();
    void playerMove(final PlayerMoved opponentMove);
}
