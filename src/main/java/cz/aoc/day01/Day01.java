package cz.aoc.day01;

import java.util.List;
import static cz.aoc.utils.InputUtils.readLines;

public class Day01 {
    public static void main(String[] args) {
        List<String> input = readLines("day01");

        int password = solveFirstPuzzle(input);
        System.out.println("First part - the password is: " + password);

        password = solveSecondPuzzle(input);
        System.out.println("Second part - the password is: " + password);
    }

    public static int solveFirstPuzzle(List<String> rotations) {
        int currDialNum = 50;
        int counter = 0;

        for (String rotation : rotations) {
            int num = Integer.parseInt(rotation.substring(1)) % 100;

            if (rotation.charAt(0) == 'L') num = -num;
            currDialNum += num;
            currDialNum %= 100;
            currDialNum = currDialNum >= 0 ? currDialNum : currDialNum + 100;

            if (currDialNum == 0) ++counter;
        }
        return counter;
    }

    public static int solveSecondPuzzle(List<String> rotations) {
        int currDialNum = 50;
        int counter = 0;

        for (String rotation : rotations) {
            int num = Integer.parseInt(rotation.substring(1));

            if (rotation.charAt(0) == 'L') num = -num;

            int prev = currDialNum;
            int newCurr = currDialNum + num;

            if (num > 0) {
                counter += Math.floorDiv(newCurr, 100);
            } else  if (num < 0) {
                counter += Math.floorDiv(prev - 1, 100) - Math.floorDiv(newCurr - 1, 100);
            }

            currDialNum = newCurr;
            currDialNum %= 100;
            currDialNum = currDialNum >= 0 ? currDialNum : currDialNum + 100;
        }
        return counter;
    }
}
