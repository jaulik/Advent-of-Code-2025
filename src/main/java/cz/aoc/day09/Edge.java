package cz.aoc.day09;

public record Edge(int x1, int y1, int x2, int y2) {
    boolean isVertical() {
        return x1 == x2;
    }
    public int getMinX() {
        return Math.min(x1, x2);
    }
    public int getMaxX() {
        return Math.max(x1, x2);
    }
    public int getMinY() {
        return Math.min(y1, y2);
    }
    public int getMaxY() {
        return Math.max(y1, y2);
    }


}
