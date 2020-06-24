package com.got.server.domain;

import com.got.server.event.PlayerJoined;
import com.got.server.service.MoveManager;
import com.got.server.service.MoveManagerFactory;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {

    private final String id;
    private PlayerStatus playerStatus;

    public Player() {
        this.id = UUID.randomUUID().toString();
        this.playerStatus = PlayerStatus.AVAILABLE;
    }

    public Player(final String id) {
        this.id = id;
        this.playerStatus = PlayerStatus.AVAILABLE;
    }

    public void joinGame() {
        this.playerStatus = PlayerStatus.OCCUPIED;
    }

    public int move(int opponentNumber, boolean isManual) {
        final MoveManager moveManager = MoveManagerFactory.getInstance(isManual);
        return moveManager.getNextNumber(opponentNumber);
    }

    public int getInitialMove(boolean isManual) {
        final MoveManager moveManager = MoveManagerFactory.getInstance(isManual);
        return moveManager.getInitialNumber();
    }

    public static Player from(final PlayerJoined playerJoined) {
        return new Player(playerJoined.getPlayerId());
    }
}
