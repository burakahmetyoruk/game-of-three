package com.got.server.service;

import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class PlayerMoveManager implements MoveManager {

    @Override
    public int getNextNumber(int number) {
        log.info("Opponent number is {}. Enter the next number:", number);
        try {
            Scanner scanner = new Scanner(System.in);
            int nextNumber = scanner.nextInt();
            if (nextNumber != NumbersCalculator.calculateNextNumber(number)) {
                log.error("Number not correct. Please try again:");
                return getNextNumber(number);
            }
            return nextNumber;
        } catch (InputMismatchException e) {
            log.error("Incorrect input. Please try again.");
            return getNextNumber(number);
        }
    }

    @Override
    public int getInitialNumber() {
        log.info("Enter the initial game number:");
        try {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            if (number <= 1) {
                log.error("Number should be greater than 1");
                return getInitialNumber();
            }
            return number;
        } catch (InputMismatchException e) {
            log.error("Incorrect input. Please try again.");
            return getInitialNumber();
        }
    }
}
