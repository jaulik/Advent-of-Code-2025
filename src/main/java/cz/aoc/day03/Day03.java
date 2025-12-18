package cz.aoc.day03;

import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day03 {
    public static void main(String[] args) {
        List<String> banks = readLines("day03");

        System.out.println("First task - total output joltage: " + solveFirstPuzzle(banks));
    }

    public static int solveFirstPuzzle(List<String> banks) {
        int sum = 0;
        for (String batteries : banks) {
            sum += findBiggestNum(batteries);
        }
        return sum;
    }

    public static int findBiggestNum(String batteries) {
        int max = 0;
        int len = batteries.length();

        for (int i = 0; i < len - 1; i++) {
            int fstDigit = Character.getNumericValue(batteries.charAt(i));
            int sndDigit = - 1;

            if ((fstDigit * 10 + 9) < max) continue;

            for (int j = i + 1; j < len; j++) {
                int currDigit = Character.getNumericValue(batteries.charAt(j));
                if (currDigit > sndDigit) {
                    sndDigit = currDigit;
                }
            }
            int currNum = fstDigit * 10 + sndDigit;
            if (max < currNum) max = currNum;
        }
        return max;
    }

}
