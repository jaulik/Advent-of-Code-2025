package cz.aoc.day03;


import org.junit.jupiter.api.Test;

import static cz.aoc.day03.Day03.findBiggestNum;
import static cz.aoc.day03.Day03.findTwelveNum;
import static org.junit.jupiter.api.Assertions.*;


public class Day03Test {

    @Test
    void testFindBiggestNum() {
        assertEquals(98, findBiggestNum("987654321111111"));
        assertEquals(89, findBiggestNum("811111111111119"));
        assertEquals(78, findBiggestNum("234234234234278"));
        assertEquals(92, findBiggestNum("818181911112111"));
    }

    @Test
    void testFindTwelveNum() {
        assertEquals(987654321111L, findTwelveNum("987654321111111"));
        assertEquals(811111111119L, findTwelveNum("811111111111119"));
        assertEquals(434234234278L, findTwelveNum("234234234234278"));
        assertEquals(888911112111L, findTwelveNum("818181911112111"));
    }
}
