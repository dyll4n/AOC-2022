package aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day02 {
    public enum Move { ROCK, PAPER, SCISSORS;}
    public record Strategy(Move opponentsMove, Move yourMove) {} //https://www.baeldung.com/java-record-keyword


    public static List<Strategy> getListOfStrategies(String input) {
        List<Strategy> strategies = new ArrayList<>();
        String[] moves = input.split("\\n");
        Move yours = null, theirs = null;

        for(int i = 0; i < moves.length; i++){
            String strategy = moves[i].trim();

            for(int j = 0; j < strategy.length(); j++){
                if(j == 0) theirs = getMove(String.valueOf(strategy.charAt(j)));
                else yours = getMove(String.valueOf(strategy.charAt(j)));
            }
            strategies.add(new Strategy(theirs, yours));
        }

        return strategies;
    }

    public static Move getMove(String input) {
        if(input.equals("A") || input.equals("X")){
            return Move.ROCK;
        } else if (input.equals("B") || input.equals("Y")) {
            return Move.PAPER;
        }else if (input.equals("C") || input.equals("Z")){
            return Move.SCISSORS;
        }
        else {
            return null;
        }
    }
}