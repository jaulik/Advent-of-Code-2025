package cz.aoc.day07;

import java.util.*;

import static cz.aoc.utils.InputUtils.readLines;

public class Day07 {
    public static final char SPLITTER = '^';

    public static void main(String[] args) {
        List<String> diagramLines = readLines("day07");

        System.out.println("The beam will split " + solveFirstPuzzle(diagramLines) + " times.");
        System.out.println("There is " + solveSecondPuzzle(diagramLines) + " total different timelines.");
    }

    public static int solveFirstPuzzle(List<String> diagramLines) {
        Set<Integer> beamIndices = new HashSet<>();
        beamIndices.add(diagramLines.getFirst().indexOf('S'));

        int splitCounter = 0;
        for (int i = 1; i < diagramLines.size(); i++) {
            Set<Integer> nextBeamIndices = new HashSet<>(beamIndices);

            for (int beamIndex : beamIndices) {
                if (diagramLines.get(i).charAt(beamIndex) == SPLITTER) {
                    nextBeamIndices.remove(beamIndex);
                    if (beamIndex > 0) nextBeamIndices.add(beamIndex - 1);
                    if (beamIndex < (diagramLines.get(i).length() - 1)) nextBeamIndices.add(beamIndex + 1);
                    splitCounter++;
                }
            }
            beamIndices = nextBeamIndices;
        }
        return splitCounter;
    }

    public static long solveSecondPuzzle(List<String> diagramLines) {
        Map<Integer, Long> timelinesCount = new HashMap<>();
        timelinesCount.put(diagramLines.getFirst().indexOf('S'), 1L);

        for (int i = 1; i < diagramLines.size(); i++) {
            Map<Integer, Long> nextTimelinesCount = new HashMap<>();

            for (Map.Entry<Integer, Long> entry : timelinesCount.entrySet()) {
                int index = entry.getKey();
                long count = entry.getValue();

                if (diagramLines.get(i).charAt(index) == SPLITTER) {
                    if (index > 0) nextTimelinesCount.merge(index - 1, count, Long::sum);
                    if (index < (diagramLines.get(i).length() - 1)) nextTimelinesCount.merge(index + 1, count, Long::sum);
                } else {
                    nextTimelinesCount.merge(index, count, Long::sum);
                }
            }
            timelinesCount = nextTimelinesCount;
        }

        return timelinesCount.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }

}
