package cz.aoc.day03;

import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day03 {
    public static void main(String[] args) {
        List<String> banks = readLines("day03");

        System.out.println("First task - total output joltage: " + solveFirstPuzzle(banks));
        System.out.println("Second task - total output joltage: " + solveSecondPuzzle(banks));
    }

    public static int solveFirstPuzzle(List<String> banks) {
        int sum = 0;
        for (String batteries : banks) {
            sum += findBiggestNum(batteries);
        }
        return sum;
    }

    public static long solveSecondPuzzle(List<String> banks) {
        long sum = 0;
        for (String batteries : banks) {
            sum += findTwelveNum(batteries);
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

    public static Long findTwelveNum(String batteries) {
        if (batteries.length() == 12) return Long.parseLong(batteries);

        return findTwelveNumRecursive(batteries, 0, 12);
    }

    private static long findTwelveNumRecursive(String batteries, int startIndex, int digitsNeeded) {
        if (digitsNeeded == 0) {
            return 0;
        }

        int remainingStringLength = batteries.length() - startIndex;
        int maxSkip = remainingStringLength - digitsNeeded;
        int searchEndIndex = startIndex + maxSkip;

        int maxDigit = -1;
        int index = -1;
        for (int i = startIndex; i <= searchEndIndex; i++) {
            int current = Integer.parseInt(String.valueOf(batteries.charAt(i)));
            if (current > maxDigit) {
                maxDigit = current;
                index = i;
            }
            if (maxDigit == 9) {
                break;
            }
        }

        long positionVal = (long) Math.pow(10, digitsNeeded - 1);
        return (maxDigit * positionVal) + findTwelveNumRecursive(batteries, index + 1, digitsNeeded - 1);
    }

}
