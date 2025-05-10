package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
