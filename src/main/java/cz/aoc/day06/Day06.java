package cz.aoc.day06;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day06 {
    public static void main(String[] args) {
        List<String> worksheet = readLines("day06");
        String[] operators = worksheet.removeLast().split("\\s+");

        System.out.println("The grand total is: " + solveFirstPuzzle(operators, worksheet));

    }

    public static long solveFirstPuzzle(String[] operators, List<String> numbers) {
        List<Long[]> numsParsed = getParsedNumbers(numbers);
        int cols = operators.length;
        int rows = numsParsed.size();

        long totalResult = 0;
        for (int i = 0; i < cols; i++) {
            long currentResult = numsParsed.getFirst()[i];
            for (int j = 1; j < rows; j++) {
                if (operators[i].equals("+")) currentResult += numsParsed.get(j)[i];
                else currentResult *= numsParsed.get(j)[i];
            }
            totalResult += currentResult;
        }
        return totalResult;
    }

    public static List<Long[]> getParsedNumbers(List<String> numbersList) {
        List<Long[]> numsParsed = new ArrayList<>();
        for (String numbers : numbersList) {
            String[] numbersSplit = numbers.trim().split("\\s+");
            numsParsed.add(Arrays.stream(numbersSplit)
                    .map(Long::parseLong)
                    .toArray(Long[]::new));
        }
        return numsParsed;
    }
}
