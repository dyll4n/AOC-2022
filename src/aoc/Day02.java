package aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {



    public enum Outcome {
        WIN, LOSE, DRAW;

        public static Outcome fromChar(char input) {
            return switch (input) {
                case 'X' -> Outcome.LOSE;
                case 'Y' -> Outcome.DRAW;
                case 'Z' -> Outcome.WIN;
                default -> throw new RuntimeException("Unexpected character for outcome: " + input);
            };
        }

        public int scoreForOutcome() {
            return switch (this) {
                case LOSE -> 0;
                case DRAW-> 3;
                case WIN -> 6;
            };
        }
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
        List<StrategyPart2> strategiesPart2 = new ArrayList<>();
        String[] moves = input.split("\\n");

        for (String move : moves) {
            String strategy = move.trim().replaceAll(" ", "");
            strategiesPart2.add(
                    new StrategyPart2(
                            Move.fromChar(strategy.charAt(0)),
                            Outcome.fromChar(strategy.charAt(1))
                    )
            );
        }
        return strategiesPart2;
    }

    // return Move here
    public static Move getMovePart2(StrategyPart2 strategy) {
        Move opponentsMove = strategy.opponentsMove;
        Outcome outcome = strategy.outcome;
        return switch (outcome) {
            case DRAW -> opponentsMove;
            case WIN -> Move.values()[Math.floorMod(opponentsMove.ordinal() + 1,  3)];
            case LOSE -> Move.values()[Math.floorMod(opponentsMove.ordinal() - 1,  3)];
        };

        //This is a fancy pants solution
        //ROCK, PAPER, SCISSORS is the name of the game. The order of moves tells you what beats what.
        //[rock, paper, scissors]
        //paper is beaten by rock. In the array above paper is at index 1 and rock is at 0. Just subtract one to determine the losing move
        //just add one to determine the winning move
        //the Math.floorMod makes sure we 'wrap around' the end of the array
        //for example: the opponent chooses scissors, and we want to beat them.
        //scissors is at index 2. We add 1 to get 3. 3 is out of bounds of the array.
        //doing Math.floorMod(3, 3) will result in 0. 0 is rock, which absolutely annihilates scissors.
    }


    public static List<Integer> getScoresForStrategiesPart2(List<StrategyPart2> strategies) {

        List<Integer> values = new ArrayList<>();

        for(StrategyPart2 strategy : strategies){
            values.add(getMovePart2(strategy).scoreForMove() + strategy.outcome.scoreForOutcome());
        }

        return values;
    }

    public static int getTotalScorePart2(String input) {
        return getScoresForStrategiesPart2(getListOfStrategiesForPart2(input))
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}