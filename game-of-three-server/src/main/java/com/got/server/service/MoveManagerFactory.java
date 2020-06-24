package com.got.server.service;

public class MoveManagerFactory {

    private static final MoveManager AUTO_MOVE_MANAGER = new AutoMoveManager();

    private static final MoveManager MANUAL_MOVE_MANAGER = new PlayerMoveManager();

    public static MoveManager getInstance(boolean isManual) {
        return isManual ? MANUAL_MOVE_MANAGER : AUTO_MOVE_MANAGER;
    }

}
