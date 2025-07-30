package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05Test {

    public String sampleInput = """
        [D]
    [N] [C]
    [Z] [M] [P]
     1   2   3
    
    move 1 from 2 to 1
    move 3 from 1 to 3
    move 2 from 2 to 1
    move 1 from 1 to 2
    """;

    @Test
    public void getSplitInputs(){
        String stacks = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3""";

        String instructions = """
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """;

        Day05.StackComponents expected = new Day05.StackComponents(stacks,instructions);
        Day05.StackComponents actual = Day05.getSplitInputs(sampleInput);

        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void canGenerateStacks(){
        List<Deque> expected = Arrays.asList(
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>()
        );

        expected.get(0).addFirst("Z");
        expected.get(0).addFirst("N");

        expected.get(1).addFirst("M");
        expected.get(1).addFirst("C");
        expected.get(1).addFirst("D");

        expected.get(2).addFirst("P");

        List<Deque> actual = Day05.generateStacks(sampleInput);

        Assertions.assertIterableEquals(actual,expected);

    }

    @Test
    public void canExecuteStackInstructions(){
        List<Deque> expected = Arrays.asList(
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>()
        );

        expected.get(0).addFirst("C");

        expected.get(1).addFirst("M");

        expected.get(2).addFirst("P");
        expected.get(2).addFirst("D");
        expected.get(2).addFirst("N");
        expected.get(2).addFirst("Z");


        List<Deque> actual = Day05.updateStacks(sampleInput);
        Assertions.assertIterableEquals(actual,expected);

    }

    @Test
    public void canGetTopElements(){
        String expected = "CMZ";
        String actual = Day05.getTopElements(sampleInput);

        Assertions.assertEquals(actual,expected);
    }

    @Test
    public void getTopElementsofPuzzle() throws IOException {
        String actualInput = Files.readString(Path.of("inputs/day05.txt"));
        String expected = "VGBBJCRMN";
        String actual = Day05.getTopElements(actualInput);

        Assertions.assertEquals(actual,expected);
    }
}
