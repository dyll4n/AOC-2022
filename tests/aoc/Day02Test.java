package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static aoc.Day02.*;
import static aoc.Day02.Move.*;

class Day02Test {

    @Test
    public void canGetListOfStrategiesFromSampleInput() {
        String input = """
                A Y
                B X
                C Z
                """;

        List<Strategy> expected = Arrays.asList(
            new Strategy(ROCK, PAPER),
            new Strategy(PAPER, ROCK),
            new Strategy(SCISSORS, SCISSORS)
        );

        List<Strategy> actual = Day02.getListOfStrategies(input);

        Assertions.assertIterableEquals(expected, actual);
    }
}