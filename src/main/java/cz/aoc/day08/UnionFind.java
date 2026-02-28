package cz.aoc.day08;

public class UnionFind {
    int[] parent;
    int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getElementSize(int i) {
        return size[i];
    }

    public boolean union(int u, int v) {
        int root1 = find(u);
        int root2 = find(v);

        if (root1 == root2) return false;

        if (size[root1] < size[root2]) {
            parent[root1] = root2;
            size[root2] += size[root1];
        } else {
            parent[root2] = root1;
            size[root1] += size[root2];
        }
        return true;
    }

    public int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
}
