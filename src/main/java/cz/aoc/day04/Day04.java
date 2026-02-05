package cz.aoc.day04;

import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day04 {
    public static void main(String[] args) {
        List<String> rollsRows = readLines("day04");
        char[][] rollsGrid = getRollsGrid(rollsRows);

        System.out.println("First task - total of "+ solveFirstPuzzle(rollsGrid) + " rolls can be accessed.");
        System.out.println("Second task - total of "+ solveSecondPuzzle(rollsGrid) + " rolls can be removed.");
    }

    public static int solveFirstPuzzle(char[][] rollsGrid) {
        int rows = rollsGrid.length;
        int cols = rollsGrid[0].length;

        int count = 0;
        for (int i = 0; i < rollsGrid.length; i++) {
            for (int j = 0; j < rollsGrid[0].length; j++) {
                if (rollsGrid[i][j] == '@') {
                    if (countNeighbors(rollsGrid, rows, cols, i, j) < 4) count++;
                }
            }
        }
        return count;
    }

    public static int solveSecondPuzzle(char[][] rollsGrid) {
        int totalCount = 0;
        int rows = rollsGrid.length;
        int cols = rollsGrid[0].length;

        // Non-zero initial value, due to the first pass through the cycle
        int count = 1;
        while (count != 0) {
            count = 0;

            for (int i = 0; i < rollsGrid.length; i++) {
                for (int j = 0; j < rollsGrid[0].length; j++) {
                    if (rollsGrid[i][j] == '@') {
                        if (countNeighbors(rollsGrid, rows, cols, i, j) < 4) {
                            count++;
                            rollsGrid[i][j] = 'X';
                        }
                    }
                }
            }
            totalCount += count;
        }
        return totalCount;
    }

    private static int countNeighbors(char[][] rollsGrid, int rows, int cols, int i, int j) {
        int neigbors = 0;

        for (int n = -1; n <= 1; n++) {
            for (int m = -1; m <= 1; m++) {
                if (i + n < 0 || i + n >= rows || j + m < 0 || j + m >= cols || (n == 0 && m == 0)) continue;
                if (rollsGrid[i + n][j + m] == '@') neigbors++;
            }
        }
        return neigbors;
    }

    private static char[][] getRollsGrid(List<String> rollsRows) {
        int rows = rollsRows.size();
        int cols = rollsRows.get(0).length();

        char[][] rollsGrid = new char[rows][cols];
        for (int i = 0; i < rollsRows.size(); i++) {
            rollsGrid[i] = rollsRows.get(i).toCharArray();
        }
        return rollsGrid;
    }

}
