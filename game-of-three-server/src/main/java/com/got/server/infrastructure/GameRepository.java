package com.got.server.infrastructure;


import com.got.server.domain.Game;
import com.got.server.domain.Player;

public interface GameRepository {

    Game findGame(String id);

    Game getGame();

    Game endGame(String id, Player winner);

    Game saveGame(Game game);
}
