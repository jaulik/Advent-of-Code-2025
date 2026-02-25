package cz.aoc.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the parsed inventory data for the Elves cafeteria.
 * <p>
 * This class stores the inclusive boundaries of fresh
 * ingredient ranges in a {@link List} to handle extremely large values without
 * exhausting heap space, alongside the list of available ingredients to be checked.
 */
public class InventoryData {
    private final List<Long[]> freshRanges;
    private final List<String> available;
    /**
     * Constructs an {@code InventoryData} instance by parsing the raw input lists.
     * * @param freshRanges A list of strings representing inclusive ranges of fresh
     * ingredient IDs (e.g., "3-5", "10-14").
     * @param available   A list of strings representing the IDs of available
     * ingredients in the inventory.
     */
    public InventoryData(List<String> freshRanges, List<String> available) {
        this.freshRanges = getFreshRangesList(freshRanges);
        this.available = available;
    }
    /**
     * Parses the list of range strings and expands them into a set of individual IDs.
     * <p>
     * * @param freshRanges A list of range strings (e.g., "3-5").
     * @return A {@link Set} containing all individual fresh ingredient IDs.
     */
    private List<Long[]> getFreshRangesList(List<String> freshRanges) {
        List<Long[]> freshRangesList = new ArrayList<>();

        for (String freshRange : freshRanges) {
            String[] ranges = freshRange.split("-");
            long start = Long.parseLong(ranges[0]);
            long end = Long.parseLong(ranges[1]);
            freshRangesList.add(new Long[] {start, end});
        }
        return freshRangesList;
    }
    /**
     * Gets the list of available ingredient IDs that need to be checked.
     * * @return A list of available ingredient IDs as strings.
     */
    public List<String> getAvailable() {
        return available;
    }
    /**
     * Gets the complete, expanded set of fresh ingredient IDs.
     * * @return A {@link Set} containing every valid, fresh ingredient ID.
     */
    public List<Long[]> getFreshRanges() {
        return freshRanges;
    }
}
