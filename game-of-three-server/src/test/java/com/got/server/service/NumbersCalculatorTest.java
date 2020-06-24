package com.got.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumbersCalculatorTest {

    @Test
    public void should_calculate_next_value() {
        final int initialNumber = 6;
        final int nextNumber = NumbersCalculator.calculateNextNumber(initialNumber);

        assertEquals(2, nextNumber);
    }

    @Test
    public void should_calculate_next_value2() {
        final int initialNumber = 7;
        final int nextNumber = NumbersCalculator.calculateNextNumber(initialNumber);

        assertEquals(2, nextNumber);
    }

    @Test
    public void should_calculate_next_value3() {
        final int initialNumber = 8;
        final int nextNumber = NumbersCalculator.calculateNextNumber(initialNumber);

        assertEquals(3, nextNumber);
    }

    @Test
    public void should_throw_illegal_argument_exception_when_number_is_lower_than_1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> NumbersCalculator.calculateNextNumber(1));
    }
}