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

        List<Strategy> strategies = getListOfStrategies(input);
        List<Integer> actual = getScoresForStrategies(strategies);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void canGetTotalScoreForStrategies() {
        Assertions.assertEquals(15, Day02.getTotalScore(input));
    }

    //may as well do final test which runs on the puzzle input
    //then do first test for part 2

}