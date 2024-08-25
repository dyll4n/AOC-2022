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

    @Test
    public void canGetRucksackCompartments() {
        List<Day03.Rucksack> expected = Arrays.asList(
                new Day03.Rucksack("vJrwpWtwJgWr","hcsFMMfFFhFp"),
                new Day03.Rucksack("jqHRNqRjqzjGDLGL","rsFMfFZSrLrFZsSL"),
                new Day03.Rucksack("PmmdzqPrV","vPwwTWBwg"),
                new Day03.Rucksack("wMqvLMZHhHMvwLH","jbvcjnnSBnvTQFn"),
                new Day03.Rucksack("ttgJtRGJ","QctTZtZT"),
                new Day03.Rucksack("CrZsJsPPZsGz","wwsLwLmpwMDw")

        );

        List<Day03.Rucksack> actual = Day03.getRucksackCompartments(sampleInput);

        Assertions.assertIterableEquals(expected, actual);

    }
}
