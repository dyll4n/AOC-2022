package aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        //hint: you can do something with ascii codes
    }
}
