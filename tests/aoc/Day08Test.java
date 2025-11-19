package aoc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Day08Test {

    public String sampleInput = """
            30373
            25512
            65332
            33549
            35390
            """;


    @Test
    public void canGetForestMap (){
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>(
                Arrays.asList(
                    new ArrayList<>(Arrays.asList(3,0,3,7,3)),
                    new ArrayList<>(Arrays.asList(2,5,5,1,2)),
                    new ArrayList<>(Arrays.asList(6,5,3,3,2)),
                    new ArrayList<>(Arrays.asList(3,3,5,4,9)),
                    new ArrayList<>(Arrays.asList(3,5,3,9,0))
                )
        );

        assertEquals(expected,Day08.getForestMap(sampleInput).mapData());
    }

    @Test
    public void canGetInnerTreeVisibility(){
        ArrayList<Day08.Pair<Point,boolean[]>> expected = new ArrayList<>(
                Arrays.asList(
                        new Day08.Pair<>(new Point(1,1), new boolean[]{true, false, true, false}),
                        new Day08.Pair<>(new Point(1,2), new boolean[]{true, false, false, true}),
                        new Day08.Pair<>(new Point(1,3), new boolean[]{false, false, false, false}),
                        new Day08.Pair<>(new Point(2,1), new boolean[]{false, false, false, true}),
                        new Day08.Pair<>(new Point(2,2), new boolean[]{false, false, false, false}),
                        new Day08.Pair<>(new Point(2,3), new boolean[]{false, false, false, true}),
                        new Day08.Pair<>(new Point(3,1), new boolean[]{false, false, false, false}),
                        new Day08.Pair<>(new Point(3,2), new boolean[]{false, true, true, false}),
                        new Day08.Pair<>(new Point(3,3), new boolean[]{false, false, false, false})
                )
        );

        ArrayList<Day08.Pair<Point,boolean[]>> actual = Day08.calculateInnerTreeVisibility(sampleInput);

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).key(),actual.get(i).key());
            assertArrayEquals(expected.get(i).value(),actual.get(i).value());
        }
    }

    @Test
    public void canGetTotalTreeVisibility(){
        assertEquals(21, Day08.calculateTotalTreeVisibility(sampleInput));
    }

    @Test
    public void canGetTotalTreeVisibilityPartOne() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day08.txt"));
        assertEquals(1789, Day08.calculateTotalTreeVisibility(actualInput));
    }
}
