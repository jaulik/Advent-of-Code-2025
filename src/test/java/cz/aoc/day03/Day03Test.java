package cz.aoc.day03;


import org.junit.jupiter.api.Test;

import static cz.aoc.day03.Day03.findBiggestNum;
import static org.junit.jupiter.api.Assertions.*;


public class Day03Test {

    @Test
    void testFindBiggestNum() {
        assertEquals(98, findBiggestNum("987654321111111"));
        assertEquals(89, findBiggestNum("811111111111119"));
        assertEquals(78, findBiggestNum("234234234234278"));
        assertEquals(92, findBiggestNum("818181911112111"));
    }
}
