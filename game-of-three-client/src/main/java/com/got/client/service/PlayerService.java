package com.got.client.service;

import com.got.client.event.PlayerMoved;

public interface PlayerService {
    void play(PlayerMoved playerMoved);

    void joinGame();
}

