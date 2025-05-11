package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day04Test {

    public String sampleInput = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """;


    @Test
    public void getAssignmentPairs(){
        List<Day04.AssignedPair> expected = Arrays.asList(
                new Day04.AssignedPair("2-4","6-8"),
                new Day04.AssignedPair("2-3","4-5"),
                new Day04.AssignedPair("5-7","7-9"),
                new Day04.AssignedPair("2-8","3-7"),
                new Day04.AssignedPair("6-6","4-6"),
                new Day04.AssignedPair("2-6","4-8")
        );
        List<Day04.AssignedPair> actual = Day04.getAssignedPairs(sampleInput);

        Assertions.assertIterableEquals(expected, actual);
    }


    @Test
    public void getAssignedSection(){
        List<Day04.AssignedSection> expected = Arrays.asList(
                new Day04.AssignedSection(new int[]{2,3,4},new int[]{6,7,8}),
                new Day04.AssignedSection(new int[]{2,3},new int[]{4,5}),
                new Day04.AssignedSection(new int[]{5,6,7},new int[]{7,8,9}),
                new Day04.AssignedSection(new int[]{2,3,4,5,6,7,8},new int[]{3,4,5,6,7}),
                new Day04.AssignedSection(new int[]{6},new int[]{4,5,6}),
                new Day04.AssignedSection(new int[]{2,3,4,5,6},new int[]{4,5,6,7,8})
        );
        List<Day04.AssignedSection> actual = Day04.getAssignedSections(sampleInput);

        Assertions.assertEquals(expected.size(),actual.size());

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i).firstSection(),actual.get(i).firstSection());
            Assertions.assertArrayEquals(expected.get(i).secondSection(),actual.get(i).secondSection());
        }

    }

    @Test
    public void getOverlappingAssignmentCount() throws IOException {
        String input = Files.readString(Path.of("inputs/day04.txt"));
        Assertions.assertEquals(305,Day04.getQuantityOverlappingPairs(input));
    }

}
