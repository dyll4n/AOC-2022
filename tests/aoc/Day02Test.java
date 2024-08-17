package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static aoc.Day02.*;
import static aoc.Day02.Move.*;

class Day02Test {

    public String sampleInput = """
                A Y
                B X
                C Z
                """;

    @Test
    public void canGetListOfStrategiesFromSampleInput() {
        List<StrategyPart1> expected = Arrays.asList(
            new StrategyPart1(ROCK, PAPER),
            new StrategyPart1(PAPER, ROCK),
            new StrategyPart1(SCISSORS, SCISSORS)
        );

        List<StrategyPart1> actual = Day02.getListOfStrategies(sampleInput);

        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void getRoundScoresFromSampleInput() {
        List<Integer> expected = Arrays.asList(
                8,
                1,
                6
        );

        List<StrategyPart1> strategies = getListOfStrategies(sampleInput);
        List<Integer> actual = getScoresForStrategies(strategies);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void canGetTotalScoreForStrategiesFromSampleInput() {
        Assertions.assertEquals(15, Day02.getTotalScore(sampleInput));
    }

    @Test
    public void totalScoreFromFileInput() throws IOException {
        String input = Files.readString(Path.of("inputs/day02part1.txt"));
        Assertions.assertEquals(12458, Day02.getTotalScore(input));
    }

    //may as well do final test which runs on the puzzle input
    //then do first test for part 2

    @Test
    public void listOfRoundStates(){
        List<State> expected = Arrays.asList(
                State.DRAW,
                State.LOSE,
                State.WIN
        );

        List<State> actual = Day02.getListOfStates(sampleInput);
        Assertions.assertIterableEquals(expected, actual);
    }
}