package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class Day03Test {

    public String sampleInput = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """;

    //renamed 'RucksackCompartments' to just Rucksack because we aren't directly asserting on compartments here
    @Test
    public void canGetRucksacks() {
        List<Day03.Rucksack> expected = Arrays.asList(
                new Day03.Rucksack("vJrwpWtwJgWr","hcsFMMfFFhFp"),
                new Day03.Rucksack("jqHRNqRjqzjGDLGL","rsFMfFZSrLrFZsSL"),
                new Day03.Rucksack("PmmdzqPrV","vPwwTWBwg"),
                new Day03.Rucksack("wMqvLMZHhHMvwLH","jbvcjnnSBnvTQFn"),
                new Day03.Rucksack("ttgJtRGJ","QctTZtZT"),
                new Day03.Rucksack("CrZsJsPPZsGz","wwsLwLmpwMDw")

        );

        List<Day03.Rucksack> actual = Day03.getRucksacks(sampleInput);

        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void canGetPriorityForItem() {
        Assertions.assertEquals(1, Day03.priorityFor('a'));
        Assertions.assertEquals(16, Day03.priorityFor('p'));
        Assertions.assertEquals(19, Day03.priorityFor('s'));
        Assertions.assertEquals(20, Day03.priorityFor('t'));
        Assertions.assertEquals(22, Day03.priorityFor('v'));
        Assertions.assertEquals(26, Day03.priorityFor('z'));
        Assertions.assertEquals(27, Day03.priorityFor('A'));
        Assertions.assertEquals(38, Day03.priorityFor('L'));
        Assertions.assertEquals(42, Day03.priorityFor('P'));
        Assertions.assertEquals(52, Day03.priorityFor('Z'));
    }

    //changed this test to just be the answer because the final step is to call sum
    //this would have been too easy for you! So I'm just going to finish it now.
    @Test
    public void canGetSumOfDuplicateItemPriorities(){
        List<Day03.Rucksack> rucksacks = Day03.getRucksacks(sampleInput);
        Assertions.assertEquals(157, Day03.sumOfDuplicateItemPriorities(rucksacks));
    }

    //Part 1 answer
    @Test
    public void canGetSumOfDuplicateItemPrioritiesForPuzzleInput() throws IOException {
        String input = Files.readString(Path.of("inputs/day03.txt")); //put your input here!
        Assertions.assertEquals(8109, Day03.sumOfDuplicateItemPriorities(Day03.getRucksacks(input)));
    }


    //Part 2 tests

    @Test
    public void canGetRucksacksInGroupsOf3() {
        List<List<Day03.Rucksack>> groupedRucksacks = Day03.getGroupedRucksacks(sampleInput);
        List<List<Day03.Rucksack>> expected = List.of(
                List.of(
                        new Day03.Rucksack("vJrwpWtwJgWr", "hcsFMMfFFhFp"),
                        new Day03.Rucksack("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL"),
                        new Day03.Rucksack("PmmdzqPrV", "vPwwTWBwg")
                ),
                List.of(
                        new Day03.Rucksack("wMqvLMZHhHMvwLH", "jbvcjnnSBnvTQFn"),
                        new Day03.Rucksack("ttgJtRGJ", "QctTZtZT"),
                        new Day03.Rucksack("CrZsJsPPZsGz", "wwsLwLmpwMDw")
                )
        );
        Assertions.assertIterableEquals(expected, groupedRucksacks);
    }
}
