package aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 {

    public record AssignedPair(String first, String second) { }

    // Could use int lists to make it easier but wanted to explore how int arrays could be used.
    public record AssignedSection(int[] firstSection, int[] secondSection) { }

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

    public static List<AssignedSection> getAssignedSections(String input) {
        List<AssignedPair> assignedPairs = getAssignedPairs(input);
        List<AssignedSection> assignedSections = new ArrayList<>();

        for(AssignedPair pair : assignedPairs){
            int startFirst = Integer.valueOf(pair.first.split("-")[0]);
            int endFirst = Integer.valueOf(pair.first.split("-")[1]);
            int firstQuantity =(endFirst - startFirst) +1;
            int[] sectionFirst = new int [firstQuantity];

            for (int i = 0; i < firstQuantity; i++) {
                sectionFirst[i] = startFirst;
                startFirst++;
            }

            int startSecond = Integer.valueOf(pair.second.split("-")[0]);
            int endSecond = Integer.valueOf(pair.second.split("-")[1]);
            int secondQuantity = (endSecond - startSecond) +1;
            int[] sectionSecond = new int [secondQuantity];

            for (int i = 0; i < secondQuantity; i++) {
                sectionSecond[i] = startSecond;
                startSecond++;
            }

            assignedSections.add(new AssignedSection(sectionFirst,sectionSecond));
        }

        return assignedSections;
    }

    public static int getQuantityOverlappingPairs(String input) {
        int count = 0;
        List<AssignedSection> assignedSections = getAssignedSections(input);
        for (AssignedSection pair: assignedSections){
            List<Integer> firstSectionConverted = Arrays.stream(pair.firstSection).boxed().toList();
            List<Integer> secondSectionConverted = Arrays.stream(pair.secondSection).boxed().toList();
            if(firstSectionConverted.containsAll(secondSectionConverted) ||
                    secondSectionConverted.containsAll(firstSectionConverted))count++;
        }

        return count;
    }

}
