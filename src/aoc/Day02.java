package aoc;

import java.util.ArrayList;
import java.util.List;

public class Day02 {
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
    }

    public record Strategy(Move opponentsMove, Move yourMove) {} //https://www.baeldung.com/java-record-keyword


    public static List<Strategy> getListOfStrategies(String input) {
        List<Strategy> strategies = new ArrayList<>();
        String[] moves = input.split("\\n");

        for (String move : moves) {
            String strategy = move.trim().replaceAll(" ", "");
            strategies.add(
                    new Strategy(
                            Move.fromChar(strategy.charAt(0)),
                            Move.fromChar(strategy.charAt(1))
                    )
            );
        }

        return strategies;
    }
}