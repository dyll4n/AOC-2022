package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {



    public record Rucksack(String firstCompartment, String secondCompartment) { }

    public static List<Rucksack> getRucksacks(String input) {
        Stream<String> lines = Arrays.stream(input
                .replaceAll("\r", "")
                .replaceAll(" ", "")
                .split("\n")
        );
        return lines.map(line ->
                new Rucksack(
                        line.substring(0, line.length() / 2),
                        line.substring(line.length() / 2)
                )
        ).collect(Collectors.toList());
    }

    public static int priorityFor(char c) {
        int priorityValue;
        int asciiValue = c;

        if(asciiValue  < 97){
            priorityValue = asciiValue  - 38;
        } else {
            priorityValue = asciiValue  - 96;
        }
        return priorityValue;
    }

    public static List<Integer> getPrioritiesFromCommonItems(List<Rucksack> rucksacks) {
        return null;
    }
}
