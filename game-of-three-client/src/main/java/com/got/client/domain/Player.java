package com.got.client.domain;

public class Player {
    private final String playerId;

    public Player(String playerId) {
        this.playerId = playerId;
    }

    public int play(int opponentNumber) {
        if (opponentNumber <= 1) {
            throw new IllegalArgumentException("Only positive numbers accepted.");
        }
        int reminder = opponentNumber % 3;
        switch (reminder) {
            case 1:
            case 2:
                return (opponentNumber + 1) / 3;
            case 0:
            default:
                return opponentNumber / 3;
        }
    }

    public String getPlayerId() {
        return playerId;
    }
}
