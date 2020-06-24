package com.got.server.service;

public class NumbersCalculator {

    public static int calculateNextNumber(int number) {
        if (number <= 1) {
            throw new IllegalArgumentException("Only positive numbers accepted.");
        }
        int reminder = number % 3;
        switch (reminder) {
            case 1:
            case 2:
                return (number + 1) / 3;
            case 0:
            default:
                return number / 3;
        }
    }
}
