package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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



    //I have changed the test to expect a list of StrategyPart2s instead of Outcomes
    //Getting just a list of outcomes creates the problem of matching up opponent moves to the correct outcomes
    //It is easier to keep them together in a new class: StrategyPart2
    @Test
    public void canGetListOfStrategiesForPart2(){
        List<StrategyPart2> expected = Arrays.asList(
                new StrategyPart2(ROCK, Outcome.DRAW),
                new StrategyPart2(PAPER, Outcome.LOSE),
                new StrategyPart2(SCISSORS, Outcome.WIN)
        );

        List<StrategyPart2> actual = Day02.getListOfStrategiesForPart2(sampleInput);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void getExpectedMove(){
        List<StrategyPart2> sampleMoves = Arrays.asList(
                new StrategyPart2(ROCK, Outcome.DRAW),
                new StrategyPart2(PAPER, Outcome.LOSE),
                new StrategyPart2(SCISSORS, Outcome.WIN)
        );

        for(StrategyPart2 strategy : sampleMoves){
            Assertions.assertEquals(ROCK, Day02.getMovePart2(strategy));
        }

        //alternatively to the above:
        Assertions.assertEquals(ROCK, Day02.getMovePart2(new StrategyPart2(ROCK, Outcome.DRAW)));
        Assertions.assertEquals(ROCK, Day02.getMovePart2(new StrategyPart2(PAPER, Outcome.LOSE)));
        Assertions.assertEquals(ROCK, Day02.getMovePart2(new StrategyPart2(SCISSORS, Outcome.WIN)));
        //when it comes to testing, the usual DRY (don't repeat yourself) rule does NOT apply.
        //in this test it is more readable to just list out the assertions
    }

    //This is a great opportunity to introduces parameterised tests. Basically they just repeat a test with different inputs and outputs.
    @ParameterizedTest
    @MethodSource("provideStrategiesForExpectedMoves")
    public void getExpectedMoveParameterised(StrategyPart2 strategy, Move expectedMove) { //note the parameters here
        Assertions.assertEquals(expectedMove, Day02.getMovePart2(strategy));
    }

    //Exhaustive stream of all possibilities
    private static Stream<Arguments> provideStrategiesForExpectedMoves() {
        //Each Arguments object has the strategy then the expected move for that strategy
        return Stream.of(
                Arguments.of(new StrategyPart2(ROCK, Outcome.WIN), PAPER),
                Arguments.of(new StrategyPart2(ROCK, Outcome.LOSE), SCISSORS),
                Arguments.of(new StrategyPart2(ROCK, Outcome.DRAW), ROCK),
                Arguments.of(new StrategyPart2(PAPER, Outcome.WIN), SCISSORS),
                Arguments.of(new StrategyPart2(PAPER, Outcome.LOSE), ROCK),
                Arguments.of(new StrategyPart2(PAPER, Outcome.DRAW), PAPER),
                Arguments.of(new StrategyPart2(SCISSORS, Outcome.WIN), ROCK),
                Arguments.of(new StrategyPart2(SCISSORS, Outcome.LOSE), PAPER),
                Arguments.of(new StrategyPart2(SCISSORS, Outcome.DRAW), SCISSORS)
        );
    }
    //What a beauty! 😍


    //Time to go in for the kill and beat this day
    @Test
    public void canGetTotalScoreForPart2FromSampleInput() {
        Assertions.assertEquals(12, Day02.getTotalScorePart2(sampleInput));
        //Hint: we can reuse code from part 1 to calculate the score.
        //don't be afraid to copy and past at first then refactor to DRY
    }

}