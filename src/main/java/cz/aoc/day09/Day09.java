package cz.aoc.day09;

import java.util.ArrayList;
import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day09 {
    public static void main(String[] args) {
        List<Point> redTiles = getRedTiles(readLines("day09"));

        System.out.println("The largest possible area is " + solveFirstPuzzle(redTiles));
    }

    public static long solveFirstPuzzle(List<Point> redTiles) {
        long maxArea = 0;

        int size = redTiles.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                long currArea = (long) (Math.abs(redTiles.get(i).x() - redTiles.get(j).x()) + 1) *
                        (Math.abs(redTiles.get(i).y() - redTiles.get(j).y()) + 1);
                maxArea = Math.max(maxArea, currArea);
            }
        }
        return maxArea;
    }


    private static List<Point> getRedTiles(List<String> lines) {
        List<Point> redTiles = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            redTiles.add(new Point(i,
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])));
        }
        return redTiles;
    }
}
