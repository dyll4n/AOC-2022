package aoc;

import java.util.Collections;
import java.util.List;

public class Day02 {
    public enum Move { ROCK, PAPER, SCISSORS;}
    public record Strategy(Move opponentsMove, Move yourMove) {} //https://www.baeldung.com/java-record-keyword


    public static List<Strategy> getListOfStrategies(String input) {
        return Collections.emptyList();
    }
}