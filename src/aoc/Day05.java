package aoc;

import java.util.*;

public class Day05 {

    public record StackComponents (String initialStacks, String instructions) {}

    public static StackComponents getSplitInputs (String input){
        String[] inputs = input.split("(\\n\\n|\\r\\n\\r\\n)");
        return new StackComponents(inputs[0],inputs[1]);
    }

    public static List<Deque> generateStacks(String sampleInput) {
        List<Deque> stacks = new ArrayList<>();
        StackComponents components = getSplitInputs(sampleInput);
        List<String> stackState = Arrays.asList(components.initialStacks.split("\n")).reversed();

        // Start creating stacks
        String [] stackNumbers = stackState.getFirst().trim().split("\\s+");
        int stackQuantity = stackNumbers.length;
        for (int i = 0; i < stackQuantity; i++) {
            stacks.add(new ArrayDeque<>());
        }

        //Popluate stacks
        for (int i = 1; i < stackState.size(); i++) {
            stackState.set(i, stackState.get(i).replaceAll("[\\[\\]]", " "));
            String stackContents = stackState.get(i);
            for (int stackIndex = 0; stackIndex < stackQuantity; stackIndex++) {
                int elementIndex = 1 + stackIndex * 4;
                if (elementIndex < stackContents.length()){
                    char element = stackContents.charAt(elementIndex);
                    if (element != ' '){
                        stacks.get(stackIndex).addFirst(String.valueOf(element));
                    }
                }

            }
        }

        return stacks;
    }


    public static List<Deque> updateStacks(String sampleInput) {
        StackComponents components = getSplitInputs(sampleInput);
        List<Deque> stacks = generateStacks(sampleInput);
        String [] instructions = components.instructions().split("(\\n|\\r\\n)");

        for (int i = 0; i < instructions.length; i++) {
            String [] instruction = instructions[i].split(" ");
            int movingQuantity = Integer.parseInt(instruction[1]);
            int startingStack = (Integer.parseInt(instruction[3]))- 1;
            int finishingStack = (Integer.parseInt(instruction[5])) -1;

            for(int j = movingQuantity; j > 0; j--){
                String value = stacks.get(startingStack).pop().toString();
                stacks.get(finishingStack).addFirst(value);
            }
        }

        return stacks;
    }

    public static String getTopElements(String sampleInput) {
        String topElements = "";
        List<Deque> stacks = updateStacks(sampleInput);
        for (Deque stack : stacks){
            topElements += stack.getFirst();
        }

        return topElements;
    }
}
