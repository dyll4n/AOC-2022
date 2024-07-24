package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static aoc.Day02.*;
import static aoc.Day02.Move.*;

class Day02Test {

    public String input = """
                A Y
                B X
                C Z
                """;

    @Test
    public void canGetListOfStrategiesFromSampleInput() {
        List<Strategy> expected = Arrays.asList(
            new Strategy(ROCK, PAPER),
            new Strategy(PAPER, ROCK),
            new Strategy(SCISSORS, SCISSORS)
        );

        List<Strategy> actual = Day02.getListOfStrategies(input);

        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void getRoundScoresFromSampleInput() {
        List<Integer> expected = Arrays.asList(
                8,
                1,
                6
        );

        List<Strategy> actual = Day02.getListOfStrategies(input);
        Assertions.assertIterableEquals(expected, actual);
    }

}