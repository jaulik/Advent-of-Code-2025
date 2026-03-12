package cz.aoc.day10;

import java.util.*;

import static cz.aoc.utils.InputUtils.readLines;

public class Day10 {
    public static void main(String[] args) {
        List<String> lines = readLines("day10");

        System.out.println("The fewest button presses required to" +
                " configure the lights on all of the machines is: " + solveFirstPuzzle(lines));
    }

    public static int solveFirstPuzzle(List<String> lines) {
        return lines.stream()
                .mapToInt(Day10::solveMachineLights)
                .filter(value -> value >= 0)
                .sum();
    }

    /**
     * Parses a single machine's configuration line and calculates the minimum button presses needed.
     *
     * @param line A string containing the initial lights diagram, button schematics, and joltage requirements.
     * @return The fewest button presses required to reach the target lights configuration,
     * or -1 if no path is found.
     */
    private static int solveMachineLights(String line) {
        String[] parts = line.split(" ");
        String lights = parts[0].substring(1, parts[0].length() - 1);
        int targetLights = parseTargetLightMask(lights);
        List<Integer> buttons = parseButtonToggleMasks(parts);
        return bfsToggleLights(buttons, targetLights, lights.length());
    }
    /**
     * Uses BFS to find the shortest path from all lights off to the target configuration.
     *
     * @param buttons      A list of integer bitmasks, where each mask represents the lights toggled by a specific button.
     * @param targetLights An integer bitmask representing the desired final state of the indicator lights.
     * @param numLights    The total number of indicator lights on the machine.
     * @return The fewest button presses required to reach the target lights configuration,
     * or -1 if no path is found.
     */
    private static int bfsToggleLights(List<Integer> buttons, int targetLights, int numLights) {
        Queue<int[]> buttonsQueue = new LinkedList<>();
        // Array to keep track of visited states to avoid infinite loops.
        // 1 << numLights equals 2^numLights, covering all possible on/off combinations.
        boolean[] visited = new boolean[1 << numLights];
        // Queue contains fields: [current state, number of presses]
        buttonsQueue.add(new int[]{0, 0});
        visited[0] = true;
        while (!buttonsQueue.isEmpty()) {
            int[] currentButton = buttonsQueue.poll();
            int state = currentButton[0];
            int presses = currentButton[1];
            if (state == targetLights) return presses;

            for (int button : buttons) {
                int nextState = state ^ button;
                if (!visited[nextState]) {
                    buttonsQueue.add(new int[]{nextState, presses + 1});
                    visited[nextState] = true;
                }
            }
        }
        return -1;
    }

    private static int parseTargetLightMask(String lights) {
        int numLights = lights.length();
        int targetLights = 0;
        for (int i = 0; i < numLights; i++) {
            if (lights.charAt(i) == '#') {
                // Sets the bit at position i to 1
                targetLights |= (1 << i);
            }
        }
        return targetLights;
    }

    private static List<Integer> parseButtonToggleMasks(String[] lineParts) {
        List<Integer> buttons = new ArrayList<>();
        for (int i = 1; i < lineParts.length - 1; i++) {
            String nums = lineParts[i].substring(1, lineParts[i].length() - 1);
            int buttonMask = 0;
            for (String num : nums.split(",")) {
                int bit = Integer.parseInt(num.trim());
                buttonMask |= (1 << bit);
            }
            buttons.add(buttonMask);
        }
        return buttons;
    }
    // helper method for Part 2
    private static int[] parseTargetJoltage(String joltage) {        
        return Arrays.stream(joltage
                        .substring(1, joltage.length() - 1)
                        .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
