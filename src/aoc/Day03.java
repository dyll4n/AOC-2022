package aoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {




    public record Rucksack(String firstCompartment, String secondCompartment) { }

    public static List<Rucksack> getRucksacks(String input) {
        Stream<String> lines = Arrays.stream(input
                .replaceAll("\r", "")
                .replaceAll(" ", "")
                .split("\n")
        );
        return lines.map(line ->
                new Rucksack(
                        line.substring(0, line.length() / 2),
                        line.substring(line.length() / 2)
                )
        ).collect(Collectors.toList());
    }

    public static int priorityFor(char c) {
        int priorityValue;
        if(c < 97){ //Java lets you implicitly treat chars as ints. I'm conflicted about whether this is a good thing or not.
            priorityValue = c - 38;
        } else {
            priorityValue = c - 96;
        }
        return priorityValue;
    }

    public static int sumOfDuplicateItemPriorities(List<Rucksack> rucksacks) {
        return rucksacks.stream().map(r -> {
            Set<Character> intersection = getStringIntersection(r.firstCompartment, r.secondCompartment);
            if (intersection.size() != 1) throw new RuntimeException("Expected 1 element in " + intersection);
            return intersection.stream().findFirst().get();
        }).mapToInt(Day03::priorityFor).sum();
    }

    /**
     * Convenience method because turning a String into a list of Characters is annoying in Java
     * and so are set operations
     * @param a The first string
     * @param b The second string
     * @return A set containing only the characters in both a and b
     */
    private static Set<Character> getStringIntersection(String a, String b) {
        Set<Character> aSet = a.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<Character> bSet = b.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        aSet.retainAll(bSet); //set intersection
        return aSet;
    }

    public static List<List<Rucksack>> getGroupedRucksacks(String input) {
        List<Rucksack> rucksacks = getRucksacks(input);
        List<List<Rucksack>> groupedRucksacks = new ArrayList<>();
        List<Rucksack> temp;

        for (int i = 0; i < rucksacks.size(); i++) {
            if (i % 3 == 0) {
                temp = rucksacks.subList(i, i + 3);
                groupedRucksacks.add(new ArrayList<>(temp));
            }
        }
        return groupedRucksacks;
    }

    public static List<String[]> mergeCompartments(List<List<Rucksack>> groupedRucksacks){
        List<String []> mergedBags = new ArrayList<>();

        for(List<Rucksack> group: groupedRucksacks){
            String [] bagGroups = new String [3];
            for (int i = 0; i < bagGroups.length; i++) {
                bagGroups[i] = group.get(i).firstCompartment + group.get(i).secondCompartment;
            }
            mergedBags.add(bagGroups);
        }

       return mergedBags;
    }

    public static List<Character> findBadgeType(List<List<Rucksack>> groupedRucksacks) {
        List<Character> badges = new ArrayList<>();
        List<String[]> mergedBags = mergeCompartments(groupedRucksacks);

        for(String[] group: mergedBags){
            Set <Character> thing = getStringIntersection(getStringIntersection(group[0],group[1]).toString(),group[2]);
            String things = thing.toString();
            badges.add(things.charAt(1));
        }
        return badges;
    }
}
