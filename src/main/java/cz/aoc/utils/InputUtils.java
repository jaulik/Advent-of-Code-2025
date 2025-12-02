package cz.aoc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputUtils {
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