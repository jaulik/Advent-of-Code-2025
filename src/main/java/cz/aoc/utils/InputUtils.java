package cz.aoc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for handling file input operations for Advent of Code puzzles.
 */
public class InputUtils {
    /**
     * Reads all lines from an input text file corresponding to a specific day.
     * <p>
     * The method constructs the file path by looking in the {@code src/main/resources/}
     * directory. For example, passing {@code "day01"} will attempt to read from
     * {@code src/main/resources/day01.txt}.
     *
     * @param day The name of the day or file prefix (e.g., "day01") to load.
     * @return A {@link List} of strings, where each element is a line from the file.
     * @throws RuntimeException if the file does not exist, or if an I/O error occurs
     * during reading.
     */
    public static List<String> readLines(String day) {
        try {
            // file in src/main/resources/dayXX.txt
            var path = Path.of("src", "main", "resources", day + ".txt");
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load input: " + day, e);
        }
    }
}