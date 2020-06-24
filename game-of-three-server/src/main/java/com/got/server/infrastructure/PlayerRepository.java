package com.got.server.infrastructure;


import com.got.server.domain.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository {

    Player findPlayer(String id);
    Player save(Player player);
}
