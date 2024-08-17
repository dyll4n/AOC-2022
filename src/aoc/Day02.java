package aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    public enum Outcome {
        WIN, LOSE, DRAW
    }

    public enum Move {
        ROCK, PAPER, SCISSORS;

        public static Move fromChar(char input) {
            return switch (input) {
                case 'A', 'X' -> Move.ROCK;
                case 'B', 'Y' -> Move.PAPER;
                case 'C', 'Z' -> Move.SCISSORS;
                default -> throw new RuntimeException("Unexpected character for move: " + input);
            };
        }

        public int scoreForMove() {
            return switch (this) {
                case ROCK -> 1;
                case PAPER -> 2;
                case SCISSORS -> 3;
            };
        }

    }

    public record StrategyPart1(Move opponentsMove, Move yourMove) { //https://www.baeldung.com/java-record-keyword
        public int score() {
            return scoreForOutcome() + yourMove.scoreForMove();
        }

        private int scoreForOutcome() {
            if (opponentsMove == yourMove) return 3;

            if (yourMove == Move.ROCK && opponentsMove == Move.SCISSORS) return 6;
            if (yourMove == Move.PAPER && opponentsMove == Move.ROCK) return 6;
            if (yourMove == Move.SCISSORS && opponentsMove == Move.PAPER) return 6;

            return 0;
        }
    }

    public record StrategyPart2(Move opponentsMove, Outcome outcome) {}


    public static List<StrategyPart1> getListOfStrategies(String input) {
        List<StrategyPart1> strategies = new ArrayList<>();
        String[] moves = input.split("\\n");

        for (String move : moves) {
            String strategy = move.trim().replaceAll(" ", "");
            strategies.add(
                    new StrategyPart1(
                            Move.fromChar(strategy.charAt(0)),
                            Move.fromChar(strategy.charAt(1))
                    )
            );
        }

        return strategies;
    }

    public static List<Integer> getScoresForStrategies(List<StrategyPart1> strategies) {
        return strategies.stream()
                .map(StrategyPart1::score)
                .collect(Collectors.toList());
    }

    public static int getTotalScore(String input) {
        return getScoresForStrategies(getListOfStrategies(input))
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static List<StrategyPart2> getListOfStrategiesForPart2(String input) {
        return null;
    }
}