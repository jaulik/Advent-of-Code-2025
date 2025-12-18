package cz.aoc.day02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static cz.aoc.day02.Day02.isRepeatedTwice;
import static cz.aoc.day02.Day02.isInvalid;

class Day02Test {

    @Test
    void testIsRepeatedTwice() {
        assertTrue(isRepeatedTwice("11"));
        assertTrue(isRepeatedTwice("1212"));
        assertTrue(isRepeatedTwice("123123"));
        assertFalse(isRepeatedTwice("0"));
        assertFalse(isRepeatedTwice("12123"));
        assertFalse(isRepeatedTwice("121235"));
    }

    @Test
    void testIsInvalid() {
        assertTrue(isInvalid("11"));
        assertTrue(isInvalid("111"));
        assertTrue(isInvalid("1111111"));
        assertTrue(isInvalid("1212121212"));
        assertTrue(isInvalid("1010"));
        assertTrue(isInvalid("1188511885"));
        assertTrue(isInvalid("446446"));
        assertTrue(isInvalid("565656"));
        assertFalse(isInvalid("1"));
        assertFalse(isInvalid("56565"));
        assertFalse(isInvalid("101"));
        assertFalse(isInvalid("10"));
        assertFalse(isInvalid("12122"));
    }
}