package cz.aoc.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day05 {
    public static void main(String[] args) {
        InventoryData inventoryData = processInput();

        System.out.println("Total available fresh ingredients: " + solveFirstPuzzle(inventoryData));

    }

    public static int solveFirstPuzzle(InventoryData inventoryData) {
        List<String> available = inventoryData.getAvailable();
        List<Long[]> freshRanges = inventoryData.getFreshRanges();

        int count = 0;
        for (String id : available) {
            long availableId = Long.parseLong(id);
            boolean isFresh = false;
            for (Long[] range : freshRanges) {
                if (range[0] <= availableId && range[1] >= availableId) {
                    isFresh = true;
                    break;
                }
            }
            if (isFresh) count++;
        }
        return count;
    }

    public static InventoryData processInput() {
        List<String> freshRanges = new ArrayList<>();
        List<String> available = new ArrayList<>();

        Path p = Path.of("src", "main", "resources", "day05.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(p.toFile()))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), "")) {
                freshRanges.add(line);
            } while ((line = reader.readLine()) != null) {
                available.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new InventoryData(freshRanges, available);
    }


}
