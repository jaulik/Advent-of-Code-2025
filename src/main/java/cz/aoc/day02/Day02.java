package cz.aoc.day02;

import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day02 {
    public static void main(String[] args) {
        List<String> ranges = List.of(readLines("day02").getFirst().split(","));
        System.out.println(ranges);

        assert(isRepeatedTwice("11"));
        assert(isRepeatedTwice("1212"));
        assert(isRepeatedTwice("123123"));
        assert (!isRepeatedTwice(""));
        assert(!isRepeatedTwice("0"));
        assert(!isRepeatedTwice("12123"));
        assert(!isRepeatedTwice("121235"));

        System.out.println(solveFirstPuzzle(ranges));
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


    public static boolean isRepeatedTwice(String input) {
        if (input.length() % 2 != 0) return false;

        String fstNum = input.substring(0,input.length() / 2);
        String sndNum = input.substring(input.length() / 2);

        return fstNum.equals(sndNum);
    }
}
