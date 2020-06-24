package com.got.server.service;

import java.util.Random;

public class AutoMoveManager implements MoveManager {

    private static final Random RANDOM_GENERATOR = new Random();

    @Override
    public int getNextNumber(int number) {
        return NumbersCalculator.calculateNextNumber(number);
    }

    @Override
    public int getInitialNumber() {
        return RANDOM_GENERATOR.nextInt(1000) + 1;
    }

}
