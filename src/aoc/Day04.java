package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 {

    public record AssignedPair(String first, String second) { }

    public static List<AssignedPair> getAssignedPairs(String input) {
        Stream<String> assignedPairs = Arrays.stream(input
                .replaceAll("\r", "")
                .replaceAll(" ", "")
                .split("\n")
        );

        return assignedPairs.map(line ->
                new AssignedPair(line.split(",")[0],line.split(",")[1])
        ).collect(Collectors.toList());

    }

}
