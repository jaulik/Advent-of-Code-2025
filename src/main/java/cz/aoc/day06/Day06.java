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
        System.out.println("The new grand total is: " + solveSecondPuzzle(operators, worksheet));
    }

    public static long solveFirstPuzzle(String[] operators, List<String> numbers) {
        List<Long[]> numsParsed = getParsedNumbers(numbers);
        int cols = operators.length;

        long totalResult = 0;
        for (int i = 0; i < cols; i++) {
            long currentResult = operators[i].equals("+") ? 0 : 1;

            for (Long[] nums : numsParsed) {
                if (operators[i].equals("+")) currentResult += nums[i];
                else currentResult *= nums[i];
            }
            totalResult += currentResult;
        }
        return totalResult;
    }

    public static long solveSecondPuzzle(String[] operators, List<String> numbers) {
        int rows = numbers.size();
        int maxLen = numbers.stream().mapToInt(String::length).max().orElse(0);
        // Pad all strings to the same length with spaces to avoid IndexOutOfBounds
        List<String> numsPadded = getNumbersPadded(numbers, maxLen);

        int numIndex = 0;
        long totalResult = 0;
        for (String operator : operators) {
            long currentResult = operator.equals("+") ? 0 : 1;

            StringBuilder sb = new StringBuilder().append(" ");
            while (!sb.isEmpty() && numIndex < maxLen) {
                sb.delete(0, sb.length());
                for (int j = 0; j < rows; j++) {
                    char currChar = numsPadded.get(j).charAt(numIndex);
                    if (currChar != ' ') sb.append(currChar);
                }
                if (!sb.isEmpty()) {
                    long currNum = Long.parseLong(sb.toString());
                    if (operator.equals("+")) currentResult += currNum;
                    else currentResult *= currNum;
                }
                numIndex++;
            }
            totalResult += currentResult;
        }
        return totalResult;
    }

    private static List<Long[]> getParsedNumbers(List<String> numbersList) {
        List<Long[]> numsParsed = new ArrayList<>();
        for (String numbers : numbersList) {
            String[] numbersSplit = numbers.trim().split("\\s+");
            numsParsed.add(Arrays.stream(numbersSplit)
                    .map(Long::parseLong)
                    .toArray(Long[]::new));
        }
        return numsParsed;
    }

    private static List<String> getNumbersPadded(List<String> numbersList, int maxLen) {
        List<String> numbersPadded = new ArrayList<>();
        for (String numbers : numbersList) {
            int currentLen = numbers.length();
            numbersPadded.add(numbers + " ".repeat(maxLen - currentLen));
        }
        return numbersPadded;
    }
}
