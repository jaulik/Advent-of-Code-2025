package cz.aoc.day08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static cz.aoc.utils.InputUtils.readLines;

public class Day08 {
    public static final int CONNECTIONS_TO_MAKE = 1000;

    public static void main(String[] args) {
        List<Point> points = getPointsFromInput(readLines("day08"));
        List<Edge> edges = getEdgesFromPoints(points);
        edges.sort(Edge::compareTo);

        System.out.println("The result of multiplying the sizes of the three largest circuits is " +
                solveFirstPuzzle(points, edges));
        System.out.println("The result of multiplying the X coordinates of the last two boxes is " +
                solveSecondPuzzle(points, edges));
    }

    public static long solveFirstPuzzle(List<Point> points, List<Edge> edgesSorted) {
        UnionFind unionFind = new UnionFind(points.size());
        int limit = Math.min(edgesSorted.size(), CONNECTIONS_TO_MAKE);
        for (int i = 0; i < limit; i++) {
            Edge edge = edgesSorted.get(i);
            unionFind.union(edge.u(), edge.v());
        }
        // find circuits and get their sizes
        List<Integer> circuitsSizes = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if (unionFind.find(i) == i) circuitsSizes.add(unionFind.getElementSize(i));
        }
        // descending order
        circuitsSizes.sort(Comparator.reverseOrder());
        long result = 1;
        for (int i = 0; i < 3; i++) result *= circuitsSizes.get(i);
        return result;
    }

    public static long solveSecondPuzzle(List<Point> points, List<Edge> edgesSorted) {
        UnionFind unionFind = new UnionFind(points.size());
        // N points need N-1 edges to connect fully
        int neededConnections = points.size() - 1;
        int currentConnections = 0;

        for (Edge edge : edgesSorted) {
            // If roots are different, this is a valid, new connection
            if (unionFind.union(edge.u(), edge.v())) currentConnections++;

            if (currentConnections == neededConnections) {
                return (long) points.get(edge.u()).x() * points.get(edge.v()).x();
            }
        }
        // Fallback in case the points can't be fully connected (shouldn't happen)
        return -1;
    }

    private static List<Point> getPointsFromInput(List<String> lines) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            Point newPoint = new Point(i,
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]));
            points.add(newPoint);
        }
        return points;
    }

    private static List<Edge> getEdgesFromPoints(List<Point> points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);

                long distanceX = p1.x() - p2.x();
                long distanceY = p1.y() - p2.y();
                long distanceZ = p1.z() - p2.z();
                long distanceSquared = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
                edges.add(new Edge(distanceSquared, i, j));
            }
        }
        return edges;
    }

}
