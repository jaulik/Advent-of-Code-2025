package cz.aoc.day08;

public record Edge(long distanceSquared, int u, int v) implements Comparable<Edge> {

    @Override
    public int compareTo(Edge o) {
        return Long.compare(distanceSquared, o.distanceSquared);
    }

}
