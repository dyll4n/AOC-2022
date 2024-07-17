package aoc;

import aoc.Day01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day01Test {

    Day01 day01 = new Day01();

    @Test
    public void canParseInput() {
        List<Integer> actual = day01.parseInput(input);
        List<Integer> expected = List.of(
                6000,
                4000,
                11000,
                24000,
                10000
        );
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void findHighestCalories(){
        Assertions.assertEquals(24000, day01.getHighestCalories(input));
    }

    @Test
    public void part1() throws IOException {
        String input = Files.readString(Path.of("inputs/day01part1.txt"));
        Assertions.assertEquals(67450, day01.getHighestCalories(input));
    }

    @Test
    public void getSumOfTopThreeElvesCalories(){
        Assertions.assertEquals(45000,day01.getSumOfTopThreeElvesCalories(input));
    }

    @Test
    public void part2() throws IOException {
        String input = Files.readString(Path.of("inputs/day01part1.txt"));
        Assertions.assertEquals(199357, day01.getSumOfTopThreeElvesCalories(input));
    }


    String input = """
            1000
            2000
            3000
                        
            4000
                        
            5000
            6000
                        
            7000
            8000
            9000
                        
            10000
            """;
}