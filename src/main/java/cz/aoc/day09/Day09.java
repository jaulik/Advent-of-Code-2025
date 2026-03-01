package cz.aoc.day09;

import java.util.ArrayList;
import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day09 {
    public static void main(String[] args) {
        List<Point> redTiles = getRedTiles(readLines("day09"));

        System.out.println("The largest possible area is " + solveFirstPuzzle(redTiles));
        System.out.println("The largest possible area using only red and green tiles is " + solveSecondPuzzle(redTiles));
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

    public static long solveSecondPuzzle(List<Point> redTiles) {
        List<Edge> borders = getBorders(redTiles);
        long maxArea = 0;

        int size = redTiles.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int minX = Math.min(redTiles.get(i).x(), redTiles.get(j).x());
                int minY = Math.min(redTiles.get(i).y(), redTiles.get(j).y());
                int maxX = Math.max(redTiles.get(i).x(), redTiles.get(j).x());
                int maxY = Math.max(redTiles.get(i).y(), redTiles.get(j).y());

                long currArea = (long) (maxX - minX + 1) * (maxY - minY + 1);
                if (currArea <= maxArea) continue;

                if (!intersectsInterior(minX, maxX, minY, maxY, borders) &&
                isCenterInside(minX, maxX, minY, maxY, borders)) maxArea = currArea;
            }
        }
        return maxArea;
    }

    private static boolean isCenterInside(int minX, int maxX, int minY, int maxY, List<Edge> borders) {
        double testX = minX + (maxX - minX) / 2.0;
        double testY = minY + (maxY - minY) / 2.0;

        // the center lies on the border
        for (Edge border : borders) {
            if (border.isVertical() && border.x1() == testX && testY >= border.getMinY() && testY <= border.getMaxY()) return true;
            if (!border.isVertical() && border.y1() == testY && testX >= border.getMinX() && testX <= border.getMaxX()) return true;
        }
        // avoid exact integers so that the ray does not hit the vertex
        double rayY = testY;
        if (rayY % 1.0 == 0.0) rayY += 0.1;

        int intersections = 0;
        for (Edge border : borders) {
            if (border.isVertical()) {
                if (border.x1() > testX && border.getMinY() < rayY
                        && border.getMaxY() > rayY) intersections++;
            }
        }
        return intersections % 2 == 1;
    }


    private static boolean intersectsInterior(int minX, int maxX, int minY, int maxY, List<Edge> borders) {
        for (Edge border : borders) {
            if (border.isVertical()) {
                if (border.x1() > minX && border.x1() < maxX &&
                border.getMinY() < maxY && border.getMaxY() > minY) return true;
            } else {
                if (border.y1() > minY && border.y1() < maxY &&
                border.getMinX() < maxX && border.getMaxX() > minX) return true;
            }
        }
        return false;
    }

    private static List<Edge> getBorders(List<Point> redTiles) {
        List<Edge> borders = new ArrayList<>();
        int size = redTiles.size();
        for (int i = 0; i < size; i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % size);
            borders.add(new Edge(p1.x(), p1.y(), p2.x(), p2.y()));
        }
        return borders;
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
