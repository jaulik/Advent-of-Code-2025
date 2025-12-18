package cz.aoc.day02;

import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day02 {
    public static void main(String[] args) {
        List<String> ranges = List.of(readLines("day02").getFirst().split(","));

        // tests for helper methods
        assert(isRepeatedTwice("11"));
        assert(isRepeatedTwice("1212"));
        assert(isRepeatedTwice("123123"));
        assert(!isRepeatedTwice(""));
        assert(!isRepeatedTwice("0"));
        assert(!isRepeatedTwice("12123"));
        assert(!isRepeatedTwice("121235"));

        assert(isInvalid("1"));
        assert(isInvalid("11"));
        assert(isInvalid("111"));
        assert(isInvalid("1111111"));
        assert(isInvalid("1212121212"));
        assert(isInvalid("1010"));
        assert(isInvalid("1188511885"));
        assert(isInvalid("446446"));
        assert(isInvalid("565656"));
        assert(!isInvalid("56565"));
        assert(!isInvalid("101"));
        assert(!isInvalid("101"));
        assert(!isInvalid("10"));
        assert(!isInvalid("12122"));

        System.out.println("First part: " + solveFirstPuzzle(ranges));
        System.out.println("Second part: " + solveSecondPuzzle(ranges));
    }

    public static long solveFirstPuzzle(List<String> ranges) {
        long sum = 0;
        for (String range : ranges) {
            String[] nums = range.split("-");

            long fstRange = Long.parseLong(nums[0]);
            long lastRange = Long.parseLong(nums[1]);
            for (long num = fstRange; num < lastRange + 1; num++) {
                if (isRepeatedTwice(String.valueOf(num))) {
                    sum += num;
                }
            }
        }
        return sum;
    }

    public static long solveSecondPuzzle(List<String> ranges) {
        long sum = 0;
        for (String range : ranges) {
            String[] nums = range.split("-");
            long fstRange = Long.parseLong(nums[0]);
            long lastRange = Long.parseLong(nums[1]);
            for (long num = fstRange; num < lastRange + 1; num++) {
                if (isInvalid(String.valueOf(num))) {
                    sum += num;
                }
            }
        }
        return sum;
    }

    public static boolean isInvalid(String id) {
        int length = id.length();
        for (int i = 1; i <= length / 2; i++) {
            if (length % i != 0) continue;

            String pattern = id.substring(0, i);
            boolean isValidPattern = true;

            for (int j = i; j < length; j += i) {
                if (!pattern.equals(id.substring(j, j + i))) {
                    isValidPattern = false;
                    break;
                }
            }
            if (isValidPattern) return true;
        }
        return false;
    }

    public static boolean isRepeatedTwice(String id) {
        if (id.length() % 2 != 0) return false;

        String fstNum = id.substring(0,id.length() / 2);
        String sndNum = id.substring(id.length() / 2);

        return fstNum.equals(sndNum);
    }
}
