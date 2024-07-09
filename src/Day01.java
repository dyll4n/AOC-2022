import java.util.ArrayList;
import java.util.List;

public class Day01 {

    public List<Integer> parseInput(String input) {

        String[] lines = input.split("\\r?\\n");
        List<Integer> totalElfCalories = new ArrayList<Integer>();

        int singleElfTotal = 0;
        for (String line : lines) {

            if (line.isEmpty()) {
                totalElfCalories.add(singleElfTotal);
                singleElfTotal = 0;
            } else {
                singleElfTotal += Integer.parseInt(line);
            }

        }

        totalElfCalories.add(singleElfTotal);
        return totalElfCalories;
    }

    public int getHighestCalories(String input) {
        return parseInput(input)
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
    }
}
